package hokutosai.server.data.repository.events;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.events.TopicEvent;

public interface TopicEventRepository extends JpaRepository<TopicEvent, Integer> {

	@Query("SELECT t FROM TopicEvent t WHERE t.featured = true AND t.date >= :date AND t.startTime > :time")
	public List<TopicEvent> findFeaturedAll(@Param("date") Date currentDate, @Param("time") Time currentTime);

	@Query("SELECT t FROM TopicEvent t WHERE t.date = :date AND t.startTime <= :time AND t.endTime > :time")
	public List<TopicEvent> findDateTimeActiveAll(@Param("date") Date date, @Param("time") Time time);

}
