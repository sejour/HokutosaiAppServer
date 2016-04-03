package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException implements AuthorizationApiUserException {

	@Getter
	private AuthorizationTarget apiUser;

	public ApiUserUnauthorizedException(AuthorizationTarget apiUser) {
		super("Invalid credentials.");
		this.apiUser = apiUser;
	}

}
