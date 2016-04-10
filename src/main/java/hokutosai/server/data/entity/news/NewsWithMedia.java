package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.media.Media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "news_with_medias")
@Data
public class NewsWithMedia {

	@Id
	@Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "news_id", nullable = false)
	private Integer newsId;

	@Column(name = "media_id", nullable = false)
	private Integer mediaId;

	@Column(name = "sequence", nullable = false)
	private Integer sequence;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "media_id", insertable = false, updatable = false)
	private Media media;

	public NewsWithMedia() {}

	public NewsWithMedia(Integer newsId, Integer mediaId, Integer sequence) {
		this.sequence = sequence;
		this.newsId = newsId;
		this.mediaId = mediaId;
	}

}
