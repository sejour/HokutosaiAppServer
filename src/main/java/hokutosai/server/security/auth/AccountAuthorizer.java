package hokutosai.server.security.auth;

import hokutosai.server.data.entity.account.Account;
import hokutosai.server.data.json.account.AuthorizedAccount;
import hokutosai.server.data.repository.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAuthorizer extends Authorizer {

	@Autowired
	private AccountRepository accountRepository;

	public AuthorizedAccount authorize(AccountCredentials credentials) throws AccountUnauthorizedException, AccountForbiddenException {
		Account account = this.accountRepository.findByAccountId(credentials.getId());

		if (account == null) {
			throw new AccountUnauthorizedException(credentials.getId());
		}

		if (account.getPassword().equals(credentials.getPassword())) {
			if (isAllow(account.getRole()) && isAllow(account)) {
				return new AuthorizedAccount(account);
			}
			throw new AccountForbiddenException(account);
		}

		throw new AccountUnauthorizedException(credentials.getId());
	}

}
