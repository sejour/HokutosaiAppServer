package hokutosai.server.data.repository.shops;

import hokutosai.server.data.entity.shops.ShopLike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopLikeRepository extends JpaRepository<ShopLike, Integer> {

	public ShopLike findByAccountId(String accountId);

	public ShopLike findByShopIdAndAccountId(Integer shopId, String accountId);

}
