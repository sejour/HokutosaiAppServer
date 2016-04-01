package hokutosai.server.security.auth;

import lombok.Getter;
import hokutosai.server.data.entity.account.Account;
import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class AccountForbiddenException extends ForbiddenException {

	@Getter
	private Account account;

	public AccountForbiddenException(Account account) {
		super("The account is not allowed.");
		this.account = account;
	}

}
