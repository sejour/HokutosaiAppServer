package hokutosai.server.data.entity.shops;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shops")
public class DetailedShop extends Shop {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@OrderBy("id DESC")
	@JsonProperty("assessments")
	@Getter @Setter
	private List<ShopAssess> assessments;

	@Transient
	@JsonProperty("my_assessment")
	@Getter @Setter
	private ShopAssess myAssessment;

}
