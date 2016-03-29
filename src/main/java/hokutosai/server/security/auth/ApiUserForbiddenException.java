package hokutosai.server.security.auth;

import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException {

	public ApiUserForbiddenException(String userId) {
		super(String.format("Access is not allowed. [user_id=%s]", userId));
	}

}
