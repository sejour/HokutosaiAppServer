package hokutosai.server.data.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import hokutosai.server.data.entity.events.EventItem;

public class EventItemSpecifications {

	public static Specification<EventItem> equalDate(Date date) {
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
