package hokutosai.server.data.entity.exhibitions;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibitions")
public class DetailedExhibition extends Exhibition{

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "exhibition_id", insertable = false, updatable = false)
	@JsonProperty("assessments")
	@Getter @Setter
	private List<ExhibitionAssess> assessments;

	@Transient
	@JsonProperty("my_assessment")
	@Getter @Setter
	private ExhibitionAssess myAssessment;
}
