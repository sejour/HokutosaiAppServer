package hokutosai.server.data.json.shops;

import java.util.List;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.entity.shops.ShopAssess;
import hokutosai.server.data.json.AssessmentList;

public class ShopAssessmentList extends AssessmentList {

	@JsonProperty("shop_id")
	private Integer shopId;

	@JsonProperty("assessments")
	@Getter
	public List<ShopAssess> assessments;

	public ShopAssessmentList(Integer shopId, List<ShopAssess> assessments, Assess myAssessment) {
		super(myAssessment);
		this.shopId = shopId;
		this.assessments = assessments;
	}

}
