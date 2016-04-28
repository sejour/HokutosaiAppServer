package hokutosai.server.data.entity.news;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hokutosai.server.data.entity.media.Media;
import lombok.Data;

@Entity
@Table(name = "news")
@Data
public class TopicNews {

	@Id
	@Column(name = "news_id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer newsId;

	@Column(name = "title", nullable = false)
	private String title;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "news_id", insertable = false, updatable = false)
	@OrderBy("sequence ASC")
	@JsonIgnore
	private List<Media> medias;

	@Transient
	public String getMediaUrl() {
		if (this.medias != null && !this.medias.isEmpty()) {
			return this.medias.get(0).getUrl();
		}
		return null;
	}

	@Column(name = "topic")
	@JsonIgnore
	private Boolean isTopic;

}
