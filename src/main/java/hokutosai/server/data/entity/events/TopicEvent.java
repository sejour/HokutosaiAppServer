package hokutosai.server.data.entity.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hokutosai.server.data.json.news.Topic;
import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class TopicEvent implements Topic {

	@Id
	@Column(name = "event_id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventId;

	@Column(name = "title")
	private String title;
	
	@Column(name = "image_url")
	private String mediaUrl;

	@Override
	public String getLink() { return null; }

	@Override
	public Integer getNewsId() { return null; }
	
}
