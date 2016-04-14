package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shops")
public class ShopLikeResult {

	@Id
	@Column(name = "shop_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("shop_id")
	private Integer shopId;
	
	@Transient
	@JsonProperty("liked")
	private boolean liked;

	@Column(name = "likes_count")
	@JsonProperty("likes_count")
	private  Integer likesCount;
	
}
