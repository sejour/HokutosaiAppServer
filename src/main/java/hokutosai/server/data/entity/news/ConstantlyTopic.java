package hokutosai.server.data.entity.news;

import hokutosai.server.data.json.news.Topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "constantly_topics")
@Data
public class ConstantlyTopic implements Topic {

	@Id
	@Column(name = "id")
	@JsonIgnore
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "media_url")
	private String mediaUrl;

	@Column(name = "news_id")
	private Integer newsId;

	@Override
	public Integer getEventId() { return null; }

}
