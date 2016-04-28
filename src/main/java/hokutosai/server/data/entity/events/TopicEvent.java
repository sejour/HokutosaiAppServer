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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class TopicEvent {

	@Id
	@Column(name = "event_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("event_id")
	private Integer eventId;

	@Column(name = "title")
	@JsonProperty("title")
	private String title;

	@Column(name = "image_url")
	@JsonProperty("image_url")
	private String imageUrl;

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
