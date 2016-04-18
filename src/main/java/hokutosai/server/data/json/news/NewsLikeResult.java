package hokutosai.server.data.json.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.news.SelectableNews;

import lombok.Data;

@Data
public class NewsLikeResult {

	@JsonProperty("news_id")
	private Integer newsId;

	@JsonProperty("liked")
	private Boolean liked;

	@JsonProperty("likes_count")
	private  Integer likesCount;

	public NewsLikeResult(SelectableNews news) {
		this.newsId = news.getNewsId();
		this.liked = news.getLiked();
		this.likesCount = news.getLikesCount();
	}

}
