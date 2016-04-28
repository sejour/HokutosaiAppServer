package hokutosai.server.data.specification;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.jpa.domain.Specification;

import hokutosai.server.data.entity.events.SimpleEvent;

public class EventSpecifications {

	public static Specification<SimpleEvent> equalSimpleEventDate(Date date) {
		return date == null ? null : (root, query, cb) -> {
            return cb.equal(root.<Date>get("date"), date);
        };
    }

	public static Specification<SimpleEvent> equalSimpleEventDate(java.util.Date date) {
		return date == null ? null : (root, query, cb) -> {
            return cb.equal(root.<Date>get("date"), date);
        };
    }

	public static Specification<SimpleEvent> laterThanEndtime(Time nowDatetime) {
		return nowDatetime == null ? null : (root, query, cb) -> {
            return cb.greaterThan(root.<Time>get("endTime"), nowDatetime);
        };
    }

	public static Specification<SimpleEvent> earlierThanStarttime(Time nowDatetime) {
		return nowDatetime == null ? null : (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.<Time>get("startTime"), nowDatetime);
        };
    }

	public static Specification<SimpleEvent> filterBySimpleEventPlaceId(Integer placeId) {
		return placeId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("placeId"), placeId);
        };
    }
}
