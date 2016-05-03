package hokutosai.server.data.repository.events;

import hokutosai.server.data.entity.events.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
