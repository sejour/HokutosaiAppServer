package hokutosai.server.data.repository.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hokutosai.server.data.entity.events.SimpleEvent;

public interface SimpleEventRepository extends JpaRepository<SimpleEvent, Integer>,  JpaSpecificationExecutor<SimpleEvent> {

}
