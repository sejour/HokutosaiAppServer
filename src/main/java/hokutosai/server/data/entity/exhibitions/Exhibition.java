package hokutosai.server.data.entity.exhibitions;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Exhibition {

	@Id
	@Column(name = "exhibition_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	private String title;

	@Column(name = "exhibitors", nullable = false)
	@JsonProperty("exhibitors")
	private String exhibitors;

	@Column(name = "displays")
	@JsonProperty("displays")
	private String displays;

	@Column(name = "image_url")
	@JsonProperty("image_url")
	private String imageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exhibition_id", insertable = false, updatable = false)
	@JsonProperty("assessment_aggregate")
	private ExhibitionScore assessmentAggregate;

	@Transient
	@JsonProperty("liked")
	private Boolean liked;

	@Column(name = "likes_count")
	@JsonProperty("likes_count")
	private  Integer likesCount;
}
