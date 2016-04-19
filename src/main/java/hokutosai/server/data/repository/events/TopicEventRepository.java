package hokutosai.server.data.repository.events;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hokutosai.server.data.entity.events.TopicEvent;

public interface TopicEventRepository extends JpaRepository<TopicEvent, Integer> {

	@Query("SELECT t FROM TopicEvent t WHERE t.featured = true")
	public List<TopicEvent> findFeaturedAll();

}
