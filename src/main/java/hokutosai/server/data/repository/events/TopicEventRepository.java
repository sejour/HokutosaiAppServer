package hokutosai.server.data.repository.events;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.events.TopicEvent;

public interface TopicEventRepository extends JpaRepository<TopicEvent, Integer> {
	
}
