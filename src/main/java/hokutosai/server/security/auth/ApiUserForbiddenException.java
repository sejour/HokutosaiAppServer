package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.domain.AuthorizationApiUser;
import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException {

	@Getter
	private AuthorizationApiUser apiUser;

	public ApiUserForbiddenException(AuthorizationApiUser apiUser) {
		super("Access is not allowed.");
		this.apiUser = apiUser;
	}

}
