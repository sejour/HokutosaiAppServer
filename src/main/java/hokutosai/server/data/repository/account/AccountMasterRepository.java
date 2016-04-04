package hokutosai.server.data.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.account.AccountMaster;

public interface AccountMasterRepository extends JpaRepository<AccountMaster, String> {

	public AccountMaster findByAccountId(String accountId);

}
