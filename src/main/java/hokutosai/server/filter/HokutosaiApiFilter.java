package hokutosai.server.filter;

import hokutosai.server.data.domain.ApiAccessCertificate;
import hokutosai.server.error.HokutosaiServerException;
import hokutosai.server.security.auth.ApiUserAuthorizer;

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
import org.springframework.stereotype.Component;

@Component
public class HokutosaiApiFilter implements Filter {

	@Autowired
	private ApiUserAuthorizer apiUserAuthorizer;

	private static final Logger logger = LoggerFactory.getLogger(HokutosaiApiFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			ApiAccessCertificate certificate = this.apiUserAuthorizer.authorize(httpRequest);
			logger.info(String.format("Permit access: %s (%s)", certificate.getUserId(), certificate.getRole()));
			chain.doFilter(request, response);
		} catch (HokutosaiServerException e) {
			logger.error(String.format("Deny access: %s", e.getLogMessage()));
			((HttpServletResponse)response).sendError(e.getHttpStatus().value(), e.getMessage());
		}
	}

	@Override
	public void destroy() {
	}

}
