package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class AccountUnauthorizedException extends UnauthorizedException {

	@Getter
	private AuthorizationTarget account;

	public AccountUnauthorizedException(AuthorizationTarget account) {
		super("ID or password is incorrect.");
		this.account = account;
	}

}
