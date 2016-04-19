package hokutosai.server.data.repository.events;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.events.TopicEvent;

public interface TopicEventRepository extends JpaRepository<TopicEvent, Integer> {

	@Query("SELECT t FROM TopicEvent t WHERE t.featured = true")
	public List<TopicEvent> findFeaturedAll();

	@Query("SELECT t FROM TopicEvent t WHERE t.date = :datetime AND t.startTime <= :datetime AND t.endTime > :datetime")
	public List<TopicEvent> findDatetimeActiveAll(@Param("datetime") Date datetime);

}
