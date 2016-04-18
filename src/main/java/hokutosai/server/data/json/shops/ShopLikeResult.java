package hokutosai.server.data.json.shops;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.shops.Shop;
import lombok.Data;

@Data
public class ShopLikeResult {

	@JsonProperty("shop_id")
	private Integer shopId;
	
	@JsonProperty("liked")
	private Boolean liked;

	@JsonProperty("likes_count")
	private  Integer likesCount;
	
	public ShopLikeResult(Shop shop) {
		this.shopId = shop.getShopId();
		this.liked = shop.getLiked();
		this.likesCount = shop.getLikesCount();
	}
	
}
