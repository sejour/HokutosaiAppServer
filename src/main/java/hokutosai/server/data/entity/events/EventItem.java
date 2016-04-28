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
public class EventItem {

	@Id
	@Column(name = "event_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("event_id")
	private Integer eventId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

	@Column(name = "date", nullable = false)
	@JsonIgnore
	private Date date;

	@Column(name = "start_time", nullable = false)
	@JsonIgnore
	private Time startTime;

	@Column(name = "place_id", nullable = false)
	@JsonIgnore
	private Integer placeId;

}
