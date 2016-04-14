package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shops")
public class ShopItem {

	@Id
	@Column(name = "shop_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("shop_id")
	private Integer shopId;

	@Column(name = "name", nullable = false)
	@JsonProperty("name")
	private String name;
	
}
