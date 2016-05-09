package hokutosai.server.data.json;

import hokutosai.server.data.entity.assessments.Assess;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AssessmentList {

	@JsonProperty("my_assessment")
	@Getter
	public Assess myAssessment;

	public AssessmentList(Assess myAssessment) {
		this.myAssessment = myAssessment;
	}

}
