package hokutosai.server.data.json.shops;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.json.AssessmentList;

public class ShopAssessmentList extends AssessmentList {

	@JsonProperty("shop_id")
	private Integer shopId;

	public ShopAssessmentList(Integer shopId, List<Assess> assessments, Assess myAssessment) {
		super(assessments, myAssessment);
		this.shopId = shopId;
	}

}
