package hokutosai.server.data.repository.shops;

import hokutosai.server.data.entity.shops.ShopAssess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAssessRepository extends JpaRepository<ShopAssess, Integer> {

	public ShopAssess findByShopIdAndAccountId(Integer shopId, String accountId);

}
