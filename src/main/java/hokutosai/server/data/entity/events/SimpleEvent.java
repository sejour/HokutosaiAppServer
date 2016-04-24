package hokutosai.server.data.entity.events;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.places.Place;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
public class SimpleEvent  extends EventItem{

	@Column(name = "start_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("start_time")
	private Date startTime;

	@Column(name = "end_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("end_time")
	private Date endTime;

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
	private Boolean liked;

	@Column(name = "likes_count")
	@JsonProperty("likes_count")
	private  Integer likesCount;

	@Column(name = "featured")
	@JsonProperty("featured")
	private  Integer featured;
}
