package hokutosai.server.data.json;

import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.entity.assessments.AssessedScore;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AssessmentResponse {

	@JsonProperty("my_assessment")
	@Getter
	public Assess myAssessment;

	@JsonProperty("assessment_aggregate")
	@Getter
	public AssessedScore assessmentAggregate;

	public AssessmentResponse(Assess myAssessment, AssessedScore aggregate) {
		this.myAssessment = myAssessment;
		this.assessmentAggregate = aggregate;
	}

}
