package hokutosai.server.data.json.shops;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.Assess;
import hokutosai.server.data.entity.AssessedScore;
import hokutosai.server.data.json.AssessmentResponse;

public class ShopAssessmentResponse extends AssessmentResponse {

	@JsonProperty("shop_id")
	@Getter
	private Integer shopId;

	public ShopAssessmentResponse(Integer shopId, Assess myAssessment, AssessedScore aggregate) {
		super(myAssessment, aggregate);
		this.shopId = shopId;
	}

}
