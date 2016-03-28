package hokutosai.server.security.auth;

import javax.servlet.http.HttpServletRequest;

import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException {

	public ApiUserUnauthorizedException(HttpServletRequest request, String userId) {
		super(request.getMethod(), request.getRequestURI(), String.format("Invalid credentials. [user_id=%s]", userId));
	}

}
