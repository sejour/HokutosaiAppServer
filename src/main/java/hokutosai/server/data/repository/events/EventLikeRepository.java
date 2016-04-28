package hokutosai.server.data.repository.events;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.events.EventLike;

public interface EventLikeRepository extends JpaRepository<EventLike, Integer> {

	public List<EventLike> findByAccountId(String accountId);

	public EventLike findByEventIdAndAccountId(Integer eventId, String accountId);

}
