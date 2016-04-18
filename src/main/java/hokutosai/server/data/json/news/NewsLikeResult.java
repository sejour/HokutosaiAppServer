package hokutosai.server.data.json.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.news.SelectableNews;

import lombok.Data;

@Data
public class NewsLikeResult {

	@JsonProperty("news_id")
	private Integer newsId;

	@JsonProperty("liked")
	private boolean liked;

	@JsonProperty("likes_count")
	private  Integer likesCount;

	public NewsLikeResult(SelectableNews shop) {
		this.newsId = shop.getNewsId();
		this.liked = shop.getLiked();
		this.likesCount = shop.getLikesCount();
	}

}
