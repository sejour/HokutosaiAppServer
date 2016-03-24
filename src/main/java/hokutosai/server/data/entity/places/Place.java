package hokutosai.server.data.entity.places;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "places")
@Data
public class Place {

	@Column(name = "place_id")
	@Id
	@JsonProperty("place_id")
	private Integer placeId;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

}
