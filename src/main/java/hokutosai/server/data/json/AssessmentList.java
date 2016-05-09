package hokutosai.server.data.json;

import java.util.List;

import hokutosai.server.data.entity.assessments.Assess;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AssessmentList {

	@JsonProperty("assessments")
	@Getter
	public List<Assess> assessments;

	@JsonProperty("my_assessment")
	@Getter
	public Assess myAssessment;

	public AssessmentList(List<Assess> assessments, Assess myAssessment) {
		this.assessments = assessments;
		this.myAssessment = myAssessment;
	}

}
