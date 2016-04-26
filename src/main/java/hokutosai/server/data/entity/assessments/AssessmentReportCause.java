package hokutosai.server.data.entity.assessments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "reports_causes")
@Data
public class AssessmentReportCause {

	@Id
	@Column(name = "cause_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("cause_id")
	private String cuaseId;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	private String text;

	@Column(name = "sequence", nullable = false)
	@JsonIgnore
	private Integer sequence;

}
