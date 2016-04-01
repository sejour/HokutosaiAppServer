package hokutosai.server.security.auth;

import hokutosai.server.data.entity.account.Account;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAuthorizer {

	private static final String PERMISSION_ALLOW = "allow";

	@Autowired
	private AccountRepository accountRepository;

	public AuthorizedAccount authorize(String id, String password) throws AccountUnauthorizedException, AccountForbiddenException {
		Account account = this.accountRepository.findByAccountId(id);

		if (account == null) {
			throw new AccountUnauthorizedException(id);
		}

		if (password.equals(account.getPassword())) {
			if (account.getRole().getPermission().equals(PERMISSION_ALLOW) && account.getPermission().equals(PERMISSION_ALLOW)) {
				return new AuthorizedAccount(account);
			}
			throw new AccountForbiddenException(account);
		}

		throw new AccountUnauthorizedException(id);
	}

}
