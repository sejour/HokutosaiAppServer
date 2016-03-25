package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AssessedScore {

	@Column(name = "total_score")
	@JsonProperty("total_score")
	private Long totalScore;

	@Column(name = "assessed_count")
	@JsonProperty("assessed_count")
	private Long assessdCount;

}
