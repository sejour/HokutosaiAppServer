package hokutosai.server.data.json.exhibitions;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.exhibitions.Exhibition;
import lombok.Data;

@Data
public class ExhibitionLikeResult {

	@JsonProperty("exhibition_id")
	private Integer exhibitionId;

	@JsonProperty("liked")
	private boolean liked;

	@JsonProperty("likes_count")
	private  Integer likesCount;

	public ExhibitionLikeResult(Exhibition exhibition) {
		this.exhibitionId = exhibition.getExhibitionId();
		this.liked = exhibition.getLiked();
		this.likesCount = exhibition.getLikesCount();
	}
}
