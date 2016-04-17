package hokutosai.server.data.entity.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "events")
public class EventItem {

	@Id
	@Column(name = "event_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("event_id")
	private Integer eventId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

}
