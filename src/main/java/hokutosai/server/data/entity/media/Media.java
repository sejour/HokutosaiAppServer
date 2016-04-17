package hokutosai.server.data.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "medias")
@Data
public class Media {

	@Id
	@Column(name = "media_id")
	@JsonProperty("media_id")
	@NotNull
	private String mediaId;

	@Column(name = "news_id")
	@JsonIgnore
	private Integer newsId;

	@Column(name = "sequence")
	@JsonProperty("sequence")
	private Integer sequence;

	@Column(name = "url")
	@JsonProperty("url")
	private String url;

	@Column(name = "file_name")
	@JsonProperty("name")
	private String fileName;

	@Column(name = "type", nullable = false)
	@JsonProperty("type")
	private String type;

	public Media() { }

	public Media(String mediaId, Integer sequence, String url, String fileName, String type) {
		this.mediaId = mediaId;
		this.sequence = sequence;
		this.url = url;
		this.fileName = fileName;
		this.type = type;
	}

}
