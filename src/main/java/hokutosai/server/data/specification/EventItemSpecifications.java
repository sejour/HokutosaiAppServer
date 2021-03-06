package hokutosai.server.data.specification;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import hokutosai.server.data.entity.events.EventItem;

public class EventItemSpecifications {

	public static Specification<EventItem> filterByDate(Date date) {
		return date == null ? null : (root, query, cb) -> {
            return cb.equal(root.<Date>get("date"), date);
        };
    }

	public static Specification<EventItem> filterByPlaceId(Integer placeId) {
		return placeId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("placeId"), placeId);
        };
    }
}
