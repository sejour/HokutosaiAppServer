package hokutosai.server.data.entity.exhibitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hokutosai.server.data.entity.assessments.AssessedScore;

@Entity
@Table(name = "exhibitions_scores")
public class ExhibitionScore  extends AssessedScore{

	@Id
	@Column(name = "exhibition_id")
	@JsonIgnore
	private Integer exhibitionId;
}
