package hokutosai.server.data.repository.shops;

import java.util.Date;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.shops.ShopAssess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopAssessRepository extends JpaRepository<ShopAssess, Integer> {

	public ShopAssess findByShopIdAndAccountId(Integer shopId, String accountId);

	@Modifying
	@Transactional
	@Query("UPDATE ShopAssess a SET a.datetime = :datetime, a.score = :score, a.comment = :comment WHERE a.accountId = :accountId AND a.shopId = :shopId")
	public void updateAssess(@Param("accountId") String accountId, @Param("shopId") Integer shopId, @Param("datetime") Date datetime, @Param("score") Integer score, @Param("comment") String comment);

}
