package hokutosai.server.data.specification;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.domain.Specification;

public class SelectableNewsSpecifications {

	public static Specification<SelectableNews> filterByEventId(Integer eventId) {
		return eventId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("eventId"), eventId);
        };
    }

	public static Specification<SelectableNews> filterByShopId(Integer shopId) {
		return shopId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("shopId"), shopId);
        };
    }

	public static Specification<SelectableNews> filterByExhibitionId(Integer exhibitionId) {
		return exhibitionId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("exhibitionId"), exhibitionId);
        };
    }

}
