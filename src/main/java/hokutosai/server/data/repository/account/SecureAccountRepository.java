package hokutosai.server.data.repository.account;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.account.SecureAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecureAccountRepository extends JpaRepository<SecureAccount, String> {

	@Modifying
	@Transactional
	@Query("UPDATE SecureAccount a SET a.name = :name WHERE a.accountId = :accountId")
	public void updateName(@Param("accountId") String accountId, @Param("name") String userName);

}
