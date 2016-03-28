package hokutosai.server.security.auth;

import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException {

	public ApiUserForbiddenException(String httpMethod, String uri, String userId) {
		super(httpMethod, uri, String.format("user_id=%s", userId));
	}

}
