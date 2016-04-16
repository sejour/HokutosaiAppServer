package hokutosai.server.security.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hokutosai.server.data.entity.account.AccountMaster;
import hokutosai.server.data.repository.account.AccountMasterRepository;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.util.RandomToken;

@Component
public class AccountGenerator {

	@Autowired
	private AccountMasterRepository accountRepository;

	private static final int AUTO_LOGIN_PASS_LENGTH = 64;

	private static final String AUTO_LOGIN_ACCOUNT_ROLE = "general";

	private RandomToken randToken = new RandomToken();

	private static final int MAX_RETRY_COUNT = 100;

	public AccountMaster issue() throws InternalServerErrorException {
		int count = 1;
		String id = UUID.randomUUID().toString();
		while (this.accountRepository.exists(id)) {
			if (++count >= MAX_RETRY_COUNT) throw new InternalServerErrorException("Sorry, account failed to create.");
			id = UUID.randomUUID().toString();
		}

		String pass = randToken.generate(AUTO_LOGIN_PASS_LENGTH);

		AccountMaster account = new AccountMaster(id, pass, AUTO_LOGIN_ACCOUNT_ROLE, "allow");

		this.accountRepository.save(account);

		return account;
	}

}
