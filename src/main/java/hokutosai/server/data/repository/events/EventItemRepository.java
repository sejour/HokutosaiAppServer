package hokutosai.server.data.repository.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hokutosai.server.data.entity.events.EventItem;

public interface EventItemRepository extends JpaRepository<EventItem, Integer>,  JpaSpecificationExecutor<EventItem> {

}
