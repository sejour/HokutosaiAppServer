package hokutosai.server.data.repository.events;

import java.sql.Date;
import java.sql.Time;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hokutosai.server.data.entity.events.SimpleEvent;

public interface SimpleEventRepository extends JpaRepository<SimpleEvent, Integer>,  JpaSpecificationExecutor<SimpleEvent> {

	@Modifying
	@Transactional
	@Query("UPDATE SimpleEvent n SET n.likesCount = n.likesCount + 1 WHERE n.eventId = :eventId")
	public void incrementLikesCount(@Param("eventId") Integer eventId);

	@Modifying
	@Transactional
	@Query("UPDATE SimpleEvent n SET n.likesCount = n.likesCount - 1 WHERE n.eventId = :eventId")
	public void decrementLikesCount(@Param("eventId") Integer eventId);

	@Modifying
	@Transactional
	@Query("UPDATE SimpleEvent n SET n.date = :date, n.startTime = :startTime, n.endTime = :endTime  WHERE n.eventId = :eventId")
	public void updateTimes(@Param("eventId") Integer eventId, @Param("date") Date date, @Param("startTime") Time startTime, @Param("endTime") Time endTime);

}
