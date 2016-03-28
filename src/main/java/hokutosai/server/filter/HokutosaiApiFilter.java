package hokutosai.server.filter;

import hokutosai.server.data.entity.auth.ApiUser;
import hokutosai.server.data.repository.auth.ApiUserRepository;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HokutosaiApiFilter implements Filter {

	@Autowired
	private ApiUserRepository apiUserRepository;

	private static final Logger logger = LoggerFactory.getLogger(HokutosaiApiFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;

		String[] authorizationValues = req.getHeader("Authorization").split(",");
		if (authorizationValues.length != 2) {
			logger.info("Deny Access 1");
			response.getWriter().println("401");
			return;
		}
		logger.info("Passed 1");

		String[] userIdValues = authorizationValues[0].split("=");
		String[] accessTokenValues = authorizationValues[1].split("=");
		if (userIdValues.length != 2 || accessTokenValues.length != 2) {
			logger.info("Deny Access 2");
			response.getWriter().println("401");
			return;
		}
		logger.info("Passed 2");

		if (!userIdValues[0].equals("user_id") || !accessTokenValues[0].equals("access_token")) {
			logger.info("Deny Access 3");
			response.getWriter().println("401");
			return;
		}

		String userId = userIdValues[1];
		String accessToken = accessTokenValues[1];

		logger.info("Passed 3 [user-id=" + userId + "] [access-token=" + accessToken + "]");

		if (apiUserRepository == null) {
			logger.error("NULL");
			return;
		}

		ApiUser apiUser = apiUserRepository.findByUserId(userId);

		if (apiUser == null) {
			logger.info("Deny Access 4");
			response.getWriter().println("401");
			return;
		}
		logger.info("Passed 4");

		String resultUserId = apiUser.getUserId();
		String resultAccessToken = apiUser.getAccessToken();

		if (!resultUserId.equals(userId) || !resultAccessToken.equals(accessToken)) {
			logger.info("Deny Access 5");
			response.getWriter().println("401");
			return;
		}
		logger.info("Passed 5");

		logger.info("Permit Access: " + req.getRequestURI() + " user-id=" + userId + " access-token=" + accessToken);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
