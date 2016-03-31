package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AssessedScore {

	@Column(name = "total_score", nullable = false)
	@JsonProperty("total_score")
	private Integer totalScore;

	@Column(name = "assessed_count", nullable = false)
	@JsonProperty("assessed_count")
	private Integer assessedCount;

}
