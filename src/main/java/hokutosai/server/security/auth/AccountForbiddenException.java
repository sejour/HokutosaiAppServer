package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class AccountForbiddenException extends ForbiddenException {

	@Getter
	private AuthorizationTarget account;

	public AccountForbiddenException(AuthorizationTarget account) {
		super("The account is not allowed.");
		this.account = account;
	}

}
