package hokutosai.server.data.json.exhibitions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.data.entity.exhibitions.ExhibitionAssess;
import hokutosai.server.data.json.AssessmentList;

public class ExhibitionsAssessmentList extends AssessmentList {

	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	@JsonProperty("assessments")
	private List<ExhibitionAssess> assessments;

	public ExhibitionsAssessmentList(Integer exhibitionId, List<ExhibitionAssess> assessments, Assess myAssessment) {
		super(myAssessment);
		this.exhibitionId = exhibitionId;
		this.assessments = assessments;
	}

}
