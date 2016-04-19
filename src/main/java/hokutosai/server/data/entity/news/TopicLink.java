package hokutosai.server.data.entity.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hokutosai.server.data.json.news.Topic;
import lombok.Data;

@Entity
@Table(name = "topics_links")
@Data
public class TopicLink implements Topic {
	
	@Id
	@Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "media_url")
	private String mediaUrl;

	@Column(name = "link", nullable = false)
	private String link;

	@Override
	public Integer getEventId() { return null; }

	@Override
	public Integer getNewsId() { return null; }
	
}
