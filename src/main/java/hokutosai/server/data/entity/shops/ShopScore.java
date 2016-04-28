package hokutosai.server.data.entity.shops;

import hokutosai.server.data.entity.assessments.AssessedScore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shops_scores")
public class ShopScore extends AssessedScore {

	@Id
	@Column(name = "shop_id")
	@JsonIgnore
	private Integer shopId;

}
