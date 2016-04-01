package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class AccountUnauthorizedException extends UnauthorizedException {

	@Getter
	private String accountId;

	public AccountUnauthorizedException(String accountId) {
		super("ID or password is incorrect.");
		this.accountId = accountId;
	}

}
