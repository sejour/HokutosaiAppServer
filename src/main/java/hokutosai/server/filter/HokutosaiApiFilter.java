package hokutosai.server.filter;

import hokutosai.server.data.domain.AccessErrorLog;
import hokutosai.server.data.domain.AccessLog;
import hokutosai.server.data.domain.AuthorizationApiUser;
import hokutosai.server.error.HokutosaiServerException;
import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.log.AccessLogger;
import hokutosai.server.security.auth.ApiUserAuthorizer;
import hokutosai.server.security.auth.ApiUserForbiddenException;
import hokutosai.server.security.auth.ApiUserUnauthorizedException;

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
	private ApiUserAuthorizer apiUserAuthorizer;

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
		AuthorizationApiUser apiUser = null;

		try {
			apiUser = this.apiUserAuthorizer.authorize(httpRequest);
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

	private void handleException(Throwable threw, HttpServletRequest httpRequest, ServletResponse response, AuthorizationApiUser apiUser) {
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
