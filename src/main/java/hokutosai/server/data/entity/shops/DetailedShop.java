package hokutosai.server.data.entity.shops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shops")
public class DetailedShop extends Shop {

	@Column(name = "introduction")
	@JsonProperty("introduction")
	@Getter @Setter
	private String introduction;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@JsonProperty("score")
	@Getter @Setter
	private ShopScore assessdScore;

}
