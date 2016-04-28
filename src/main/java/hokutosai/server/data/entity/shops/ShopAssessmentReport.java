package hokutosai.server.data.entity.shops;

import hokutosai.server.data.entity.assessments.AssessmentReport;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shops_assessments_reports")
public class ShopAssessmentReport extends AssessmentReport {

	public ShopAssessmentReport() {}

	public ShopAssessmentReport(Integer assessmentId, String causeId, String senderId) {
		super(assessmentId, causeId, senderId);
	}

}
