package hokutosai.server.data.entity.assessments;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AssessmentReport {

	@Id
	@Column(name = "report_id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reportId;

	@Column(name = "assessment_id")
	private Integer assessmentId;

	@Column(name = "cause_id")
	private String causeId;

	@Column(name = "sender_id")
	private String senderId;

	public AssessmentReport() {}

	public AssessmentReport(Integer assessmentId, String causeId, String senderId) {
		this.assessmentId = assessmentId;
		this.causeId = causeId;
		this.senderId = senderId;
	}

}
