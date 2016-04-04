package hokutosai.server.data.repository.shops;

import javax.transaction.Transactional;

import hokutosai.server.data.entity.shops.DetailedShop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailedShopRepository extends JpaRepository<DetailedShop, Integer> {

	public DetailedShop findByShopId(Integer shopId);

	@Modifying
	@Transactional
	@Query("UPDATE DetailedShop s SET s.likesCount = s.likesCount + 1 WHERE s.shopId = :shopId")
	public void incrementLikesCount(@Param("shopId") Integer shopId);

	@Modifying
	@Transactional
	@Query("UPDATE DetailedShop s SET s.likesCount = s.likesCount - 1 WHERE s.shopId = :shopId")
	public void decrementLikesCount(@Param("shopId") Integer shopId);

}
