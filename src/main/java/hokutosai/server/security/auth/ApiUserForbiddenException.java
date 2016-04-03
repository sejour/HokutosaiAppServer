package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException implements AuthorizationApiUserException {

	@Getter
	private AuthorizationTarget apiUser;

	public ApiUserForbiddenException(AuthorizationTarget apiUser) {
		super("Access is not allowed.");
		this.apiUser = apiUser;
	}

}
