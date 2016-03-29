package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.domain.AuthorizationApiUser;
import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException {

	@Getter
	private AuthorizationApiUser apiUser;

	public ApiUserUnauthorizedException(AuthorizationApiUser apiUser) {
		super("Invalid credentials.");
		this.apiUser = apiUser;
	}

}
