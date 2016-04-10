package hokutosai.server.data.entity.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public NewsWithMedia() {}

	public NewsWithMedia(Integer newsId, Integer mediaId) {
		this.newsId = newsId;
		this.mediaId = mediaId;
	}

}
