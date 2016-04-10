package hokutosai.server.data.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "medias")
@Data
public class Media {

	@Id
	@Column(name = "media_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("media_id")
	private Integer mediaId;

	@Column(name = "url", nullable = false)
	@JsonProperty("url")
	private String url;

	@Column(name = "type", nullable = false)
	@JsonProperty("type")
	private String type;

	public Media() { }

	public Media(String url, String type) {
		this.url = url;
		this.type = type;
	}

}
