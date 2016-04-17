package hokutosai.server.data.specification;

import java.util.Date;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.domain.Specification;

public class SelectableNewsSpecifications {

	public static Specification<SelectableNews> laterThanNewsId(Integer sinceId) {
		return sinceId == null ? null : (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.get("newsId"), sinceId);
        };
    }

	public static Specification<SelectableNews> earlierThanNewsId(Integer lastId) {
		return lastId == null ? null : (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.get("newsId"), lastId);
        };
    }

	public static Specification<SelectableNews> laterThanDatetime(Date sinceDatetime) {
		return sinceDatetime == null ? null : (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.<Date>get("datetime"), sinceDatetime);
        };
    }

	public static Specification<SelectableNews> earlierThanDatetime(Date lastDatetime) {
		return lastDatetime == null ? null : (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.<Date>get("datetime"), lastDatetime);
        };
    }

	public static Specification<SelectableNews> filterByEventId(Integer eventId) {
		return eventId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("relatedEvent").get("eventId"), eventId);
        };
    }

	public static Specification<SelectableNews> filterByShopId(Integer shopId) {
		return shopId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("relatedShop").get("shopId"), shopId);
        };
    }

	public static Specification<SelectableNews> filterByExhibitionId(Integer exhibitionId) {
		return exhibitionId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("relatedExhibition").get("exhibitionId"), exhibitionId);
        };
    }

}
