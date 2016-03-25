package hokutosai.server.data.entity.shops;

import hokutosai.server.data.entity.places.Place;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@JoinColumn(name = "place_id", insertable = false, updatable = false)
	@JsonProperty("place")
	@Getter @Setter
	private Place place;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@JsonProperty("score")
	@Getter @Setter
	private ShopScore assessdScore;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@JsonProperty("menu")
	@Getter @Setter
	private List<MenuItem> menu;

}
