package hokutosai.server.security.auth;

import javax.servlet.http.HttpServletRequest;

import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException {

	public ApiUserForbiddenException(HttpServletRequest request, String userId) {
		super(request.getMethod(), request.getRequestURI(), String.format("user_id=%s", userId));
	}

}
