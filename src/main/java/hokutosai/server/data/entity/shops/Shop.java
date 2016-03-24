package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Shop {

	@Column(name = "shop_id") @Id @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("shop_id")
	private Integer shopId;

	@Column(name = "name") @NotNull
	@JsonProperty("name")
	private String name;

	@Column(name = "tenant") @NotNull
	@JsonProperty("tenant")
	private String tenant;

	@Column(name = "sales")
	@JsonProperty("sales")
	private String sales;

	@Column(name = "image_url")
	@JsonProperty("image_url")
	private String imageUrl;

}
