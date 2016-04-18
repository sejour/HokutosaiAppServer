package hokutosai.server.data.json.exhibitions;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.Assess;
import hokutosai.server.data.entity.AssessedScore;
import hokutosai.server.data.json.AssessmentResponse;
import lombok.Getter;

public class ExhibitionAssessmentResponse extends AssessmentResponse{

	@JsonProperty("exhibition_id")
	@Getter
	private Integer exhibitionId;

	public ExhibitionAssessmentResponse(Integer exhibitionId, Assess myAssessment, AssessedScore aggregate) {
		super(myAssessment, aggregate);
		this.exhibitionId = exhibitionId;
	}
}
