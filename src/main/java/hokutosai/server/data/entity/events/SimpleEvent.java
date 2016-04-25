package hokutosai.server.data.entity.events;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.places.Place;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Data
public class SimpleEvent{

	@Id
	@Column(name = "event_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("event_id")
	private Integer eventId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date date;

	@Column(name = "start_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("start_time")
	private Time startTime;

	@Column(name = "end_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("end_time")
	private Time endTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place_id", insertable = false, updatable = false)
	@JsonProperty("place")
	@Getter @Setter
	private Place place;

	@Column(name = "performer", nullable = false)
	@JsonProperty("performer")
	private String performer;

	@Column(name = "detail", nullable = false)
	@JsonProperty("detail")
	private String detail;

	@Column(name = "image_url")
	@JsonProperty("image_url")
	private String imageUrl;

	@Transient
	@JsonProperty("liked")
	@Getter @Setter
	private Boolean liked;

	@Column(name = "likes_count")
	@JsonProperty("likes_count")
	@Getter @Setter
	private  Integer likesCount;

	@Column(name = "featured")
	@JsonProperty("featured")
	private  Integer featured;
}
