package hokutosai.server.filter;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.entity.Endpoint;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.EndpointRepository;
import hokutosai.server.error.ErrorHandler;
import hokutosai.server.error.ForbiddenException;
import hokutosai.server.error.NotFoundException;
import hokutosai.server.error.UnauthorizedException;
import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.log.AccessLogger;
import hokutosai.server.security.auth.AccountAuthorizer;
import hokutosai.server.security.auth.ApiUserAuthorizer;
import hokutosai.server.security.auth.AuthorizationHeader;
import hokutosai.server.security.auth.Authorizer;
import hokutosai.server.util.EndpointPath;
import hokutosai.server.util.RequestAttribute;

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
		HttpServletResponse httpResponse = (HttpServletResponse)response;

		try {
			Endpoint endpoint = this.acceptRequest(httpRequest);
			AuthorizationHeader authHeader = new AuthorizationHeader(httpRequest);
			AuthorizationTarget apiUser = this.apiUserAuthorizer.authorize(authHeader.getApiUser(), endpoint);
			request.setAttribute(RequestAttribute.API_USER, apiUser);

			// Management side CORS対応
			if (apiUser.getRole().equals("management")) this.appendCorsAccessControlHeaders(httpResponse);

			String accountNeed = endpoint.getAccountNeed();
			if (!accountNeed.equals("no")) {
				if (authHeader.hasAccount()) {
					AuthorizedAccount account = this.accountAuthorizer.authorize(authHeader.getAccount(), endpoint);
					request.setAttribute(RequestAttribute.ACCOUNT, account);
				} else if (accountNeed.equals("required")) {
					throw new UnauthorizedException("Account is required to access to the endpoint.");
				}
			}

			chain.doFilter(request, response);
		}
		catch (Throwable e) {
			this.respondError(ErrorHandler.handle(e, request), httpResponse);
		}
		finally {
			this.accessLogger.log(httpRequest, httpResponse);
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

	private void respondError(ErrorResponse error, HttpServletResponse response) {
		try {
			response.setStatus(error.getHttpStatus().value());
			this.om.writeValue(response.getOutputStream(), error);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	private void appendCorsAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}

	@Override
	public void destroy() {
	}

}
