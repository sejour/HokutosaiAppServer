package hokutosai.server.data.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import hokutosai.server.data.entity.events.SimpleEvent;

public class SimpleEventSpecifications {

	public static Specification<SimpleEvent> equalSimpleEventDate(Date date) {
		return date == null ? null : (root, query, cb) -> {
            return cb.equal(root.<Date>get("date"), date);
        };
    }

	public static Specification<SimpleEvent> laterThanEndtime(Date nowDatetime) {
		return nowDatetime == null ? null : (root, query, cb) -> {
            return cb.greaterThan(root.<Date>get("endTime"), nowDatetime);
        };
    }

	public static Specification<SimpleEvent> earlierThanStarttime(Date nowDatetime) {
		return nowDatetime == null ? null : (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.<Date>get("startTime"), nowDatetime);
        };
    }

	public static Specification<SimpleEvent> filterBySimpleEventPlaceId(Integer placeId) {
		return placeId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("placeId"), placeId);
        };
    }
}
