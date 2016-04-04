package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
public abstract class Shop {

	@Id
	@Column(name = "shop_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("shop_id")
	private Integer shopId;

	@Column(name = "name", nullable = false)
	@JsonProperty("name")
	private String name;

	@Column(name = "tenant", nullable = false)
	@JsonProperty("tenant")
	private String tenant;

	@Column(name = "sales")
	@JsonProperty("sales")
	private String sales;

	@Column(name = "image_url")
	@JsonProperty("image_url")
	private String imageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@JsonProperty("assessment_aggregate")
	@Getter @Setter
	private ShopScore assessmentAggregate;

	@Transient
	@JsonProperty("liked")
	private boolean liked;

}
