package hokutosai.server.filter;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.document.log.AccessErrorLog;
import hokutosai.server.data.document.log.AccessLog;
import hokutosai.server.data.entity.Endpoint;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.EndpointRepository;
import hokutosai.server.error.ForbiddenException;
import hokutosai.server.error.HokutosaiServerException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.error.UnauthorizedException;
import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.log.AccessLogger;
import hokutosai.server.security.auth.AccountAuthorizer;
import hokutosai.server.security.auth.ApiUserAuthorizer;
import hokutosai.server.security.auth.ApiUserForbiddenException;
import hokutosai.server.security.auth.ApiUserUnauthorizedException;
import hokutosai.server.security.auth.AuthorizationHeader;
import hokutosai.server.security.auth.Authorizer;
import hokutosai.server.util.EndpointPath;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HokutosaiApiFilter implements Filter {

	@Autowired
	private EndpointRepository endpointRepository;

	@Autowired
	private ApiUserAuthorizer apiUserAuthorizer;

	@Autowired
	private AccountAuthorizer accountAuthorizer;

	@Autowired
	private AccessLogger accessLogger;

	private static final Logger logger = LoggerFactory.getLogger(HokutosaiApiFilter.class);
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		AuthorizationTarget apiUser = null;

		try {
			Endpoint endpoint = this.acceptRequest(httpRequest);
			AuthorizationHeader authHeader = new AuthorizationHeader(httpRequest);
			apiUser = this.apiUserAuthorizer.authorize(authHeader.getApiUser(), endpoint);
			request.setAttribute("ApiUser", apiUser);

			String accountNeed = endpoint.getAccountNeed();
			if (!accountNeed.equals("no")) {
				if (authHeader.hasAccount()) {
					AuthorizedAccount account = this.accountAuthorizer.authorize(authHeader.getAccount(), endpoint);
					request.setAttribute("Account", account);
				} else if (accountNeed.equals("required")) {
					throw new UnauthorizedException("Account is required to access to the endpoint.");
				}
			}

			chain.doFilter(request, response);
			this.accessLogger.access(new AccessLog(httpRequest, apiUser, HttpStatus.valueOf(((HttpServletResponse)response).getStatus())));
		}
		catch (ApiUserUnauthorizedException e) {
			this.handleException(e, httpRequest, response, e.getApiUser());
		}
		catch (ApiUserForbiddenException e) {
			this.handleException(e, httpRequest, response, e.getApiUser());
		}
		catch (Throwable e) {
			this.handleException(e, httpRequest, response, apiUser);
		}
	}

	private Endpoint acceptRequest(HttpServletRequest request) throws NotFoundException, ForbiddenException {
		EndpointPath path = new EndpointPath(request.getRequestURI());
		Endpoint endpoint = this.endpointRepository.findByPathAndMethod(path.toString(), request.getMethod());
		if (endpoint == null) throw new NotFoundException("The endpoint does not exist.");

		if (Authorizer.isAllow(endpoint.getCategory())) {
			return endpoint;
		}

		throw new ForbiddenException("Access is not allowed.");
	}

	private void handleException(Throwable threw, HttpServletRequest httpRequest, ServletResponse response, AuthorizationTarget apiUser) {
		if (threw == null) { return; }

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		for (Throwable e = threw; e != null; e = e.getCause()) {
			if (e instanceof HokutosaiServerException) {
				HokutosaiServerException hse = (HokutosaiServerException)e;
				status = hse.getHttpStatus();
				threw = e;
				break;
			}
		}

		this.accessLogger.error(new AccessErrorLog(httpRequest, apiUser, status, threw));
		this.respondError(status, status.is5xxServerError() ? null : threw.getMessage(), response);
	}

	private void respondError(HttpStatus status, String message, ServletResponse response) {
		try {
			this.om.writeValue(response.getOutputStream(), new ErrorResponse(status, message));
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@Override
	public void destroy() {
	}

}
