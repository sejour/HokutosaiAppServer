package hokutosai.server.data.repository.events;

import org.springframework.data.jpa.repository.JpaRepository;

import hokutosai.server.data.entity.events.DetailedEvent;

public interface DetailedEventRepository extends JpaRepository<DetailedEvent, Integer>{

}
