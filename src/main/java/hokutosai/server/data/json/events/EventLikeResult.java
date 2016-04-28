package hokutosai.server.data.json.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.events.SimpleEvent;
import lombok.Data;

@Data
public class EventLikeResult {

	@JsonProperty("event_id")
	private Integer eventId;

	@JsonProperty("liked")
	private Boolean liked;

	@JsonProperty("likes_count")
	private  Integer likesCount;

	public EventLikeResult(SimpleEvent event) {
		this.eventId = event.getEventId();
		this.liked = event.getLiked();
		this.likesCount = event.getLikesCount();
	}

}
