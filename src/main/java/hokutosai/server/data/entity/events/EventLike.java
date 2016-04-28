package hokutosai.server.data.entity.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import hokutosai.server.data.entity.Like;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events_likes")
public class EventLike extends Like {

	@Column(name = "event_id")
	@Getter @Setter
	private Integer eventId;

	public EventLike() {}

	public EventLike(Integer eventId, String accountId) {
		super(accountId);
		this.eventId = eventId;
	}

}
