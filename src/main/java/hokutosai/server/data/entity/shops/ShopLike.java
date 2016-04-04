package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import hokutosai.server.data.entity.Like;

@Entity
@Table(name = "shops_likes")
public class ShopLike extends Like {

	@Column(name = "shop_id")
	@Getter @Setter
	private Integer shopId;

}
