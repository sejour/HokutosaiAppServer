package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.media.Media;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "news")
@Data
public class News {

	@Id
	@Column(name = "media_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("media_id")
	private Integer newsId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

	@Column(name = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("datetime")
	private Date datetime;

	@Column(name = "event_id")
	@JsonProperty("event_id")
	private Integer eventId;

	@Column(name = "shop_id")
	@JsonProperty("shop_id")
	private Integer shopId;

	@Column(name = "exhibition_id")
	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	@Column(name = "topic", nullable = false)
	@JsonProperty("title")
	private Boolean isTopic;

	@Column(name = "tag")
	@JsonProperty("tag")
	private String tag;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	private String text;

	@Column(name = "likes_count", insertable = false)
	@JsonProperty("likes_count")
	private Integer likesCount;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "news_id", insertable = false, updatable = false)
	@JsonProperty("medias")
	private List<Media> medias;

	@Transient
	@JsonProperty("liked")
	private boolean liked;

}
