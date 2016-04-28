package hokutosai.server.data.specification;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import hokutosai.server.data.entity.events.SimpleEvent;

public class EventSpecifications {

	public static Specification<SimpleEvent> filterByDate(Date date) {
		return date == null ? null : (root, query, cb) -> {
            return cb.equal(root.<Date>get("date"), date);
        };
    }

	public static Specification<SimpleEvent> filterByPlaceId(Integer placeId) {
		return placeId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("place").get("placeId"), placeId);
        };
    }
}
