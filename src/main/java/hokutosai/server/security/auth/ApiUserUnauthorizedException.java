package hokutosai.server.security.auth;

import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException {

	public ApiUserUnauthorizedException(String userId) {
		super(String.format("Invalid credentials. [user_id=%s]", userId));
	}

}
