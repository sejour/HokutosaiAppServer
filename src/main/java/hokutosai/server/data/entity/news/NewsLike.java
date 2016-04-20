package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.Like;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news_likes")
public class NewsLike extends Like {

	@Column(name = "news_id")
	@Getter @Setter
	private Integer newsId;

	public NewsLike() {}

	public NewsLike(Integer newsId, String accountId) {
		super(accountId);
		this.newsId = newsId;
	}

}
