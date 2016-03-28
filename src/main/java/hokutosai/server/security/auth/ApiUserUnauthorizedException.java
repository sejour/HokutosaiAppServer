package hokutosai.server.security.auth;

import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException {

	public ApiUserUnauthorizedException(String httpMethod, String uri, String userId) {
		super(httpMethod, uri, String.format("user_id=%s", userId));
	}

}
