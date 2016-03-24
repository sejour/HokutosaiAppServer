package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "shops")
@Data
public class Shop {

	@Column(name = "shop_id") @Id @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("place_id")
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
