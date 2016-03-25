package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shops_menus")
@Data
public class MenuItem {

	@Id
	@Column(name = "item_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("item_id")
	private Integer itemId;

	@Column(name = "shop_id", nullable = false)
	@JsonIgnore
	private Integer shopId;

	@Column(name = "name", nullable = false)
	@JsonProperty("name")
	private String name;

	@Column(name = "price", nullable = false)
	@JsonProperty("price")
	private Integer price;

}
