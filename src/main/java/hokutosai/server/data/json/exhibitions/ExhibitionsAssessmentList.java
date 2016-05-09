package hokutosai.server.data.json.exhibitions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.json.AssessmentList;

public class ExhibitionsAssessmentList extends AssessmentList {

	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	public ExhibitionsAssessmentList(Integer exhibitionId, List<Assess> assessments, Assess myAssessment) {
		super(assessments, myAssessment);
		this.exhibitionId = exhibitionId;
	}

}
