package hokutosai.server.data.repository.shops;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.shops.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, Integer> {
	
}
