package hokutosai.server.data.entity.events;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	public Integer getNewsId() { return null; }

	@Column(name = "featured")
	@JsonIgnore
	private Boolean featured;

	@Column(name = "date")
	@JsonIgnore
    private Date date;

	@Column(name = "start_time")
    @JsonIgnore
    private Time startTime;

	@Column(name = "end_time")
    @JsonIgnore
    private Time endTime;

}
