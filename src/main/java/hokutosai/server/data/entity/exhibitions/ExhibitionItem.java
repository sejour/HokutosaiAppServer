package hokutosai.server.data.entity.exhibitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "exhibitions")
public class ExhibitionItem {

	@Id
	@Column(name = "exhibition_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

}
