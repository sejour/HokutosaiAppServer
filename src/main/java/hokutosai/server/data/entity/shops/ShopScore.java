package hokutosai.server.data.entity.shops;

import hokutosai.server.data.entity.AssessedScore;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shops_scores")
public class ShopScore extends AssessedScore {

}
