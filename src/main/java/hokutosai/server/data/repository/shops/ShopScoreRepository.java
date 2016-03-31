package hokutosai.server.data.repository.shops;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.shops.ShopScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopScoreRepository extends JpaRepository<ShopScore, Integer> {

	public ShopScore findByShopId(Integer shopId);

	@Modifying
	@Transactional
	@Query("UPDATE ShopScore s SET s.assessedCount = s.assessedCount + 1, s.totalScore = s.totalScore + :assessedScore WHERE s.shopId = :shopId")
	public void assess(@Param("shopId") Integer shopId, @Param("assessedScore") Integer score);


	@Modifying
	@Transactional
	@Query("UPDATE ShopScore s SET s.totalScore = s.totalScore - :previousScore + :assessedScore WHERE s.shopId = :shopId")
	public void reassess(@Param("shopId") Integer shopId, @Param("assessedScore") Integer score, @Param("previousScore") Integer previousScore);

}
