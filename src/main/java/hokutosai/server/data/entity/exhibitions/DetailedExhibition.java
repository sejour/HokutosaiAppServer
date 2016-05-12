package hokutosai.server.data.entity.exhibitions;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibitions")
public class DetailedExhibition extends Exhibition{

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "exhibition_id", insertable = false, updatable = false)
	@OrderBy("id DESC")
	@JsonProperty("assessments")
	@Getter @Setter
	private List<ExhibitionAssess> assessments;

}
