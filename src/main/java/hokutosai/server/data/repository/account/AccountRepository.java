package hokutosai.server.data.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	public Account findByAccountId(String accountId);

}
