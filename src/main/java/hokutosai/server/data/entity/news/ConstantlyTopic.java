package hokutosai.server.data.entity.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "constantly_topics")
@Data
public class ConstantlyTopic {

	@Id
	@Column(name = "id")
	@JsonIgnore
	private Integer id;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

	@Column(name = "media_url")
	@JsonProperty("media_url")
	private String mediaUrl;

	@Column(name = "news_id")
	@JsonProperty("news_id")
	private Integer newsId;

}
