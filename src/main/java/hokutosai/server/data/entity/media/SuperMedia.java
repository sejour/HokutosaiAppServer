package hokutosai.server.data.entity.media;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class SuperMedia {

	@Id
	@Column(name = "media_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("media_id")
	@NotNull
	private Integer mediaId;

	@Column(name = "url")
	@JsonProperty("url")
	private String url;

	@Column(name = "file_name")
	@JsonProperty("name")
	private String fileName;

	@Column(name = "type", nullable = false)
	@JsonProperty("type")
	private String type;

	public SuperMedia() { }

	public SuperMedia(String type) {
		this.type = type;
	}

}
