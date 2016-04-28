package hokutosai.server.data.entity.exhibitions;

import hokutosai.server.data.entity.assessments.AssessmentReport;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "exhibitions_assessments_reports")
public class ExhibitionAssessmentReport extends AssessmentReport {

	public ExhibitionAssessmentReport() {}

	public ExhibitionAssessmentReport(Integer assessmentId, String causeId, String senderId) {
		super(assessmentId, causeId, senderId);
	}

}
