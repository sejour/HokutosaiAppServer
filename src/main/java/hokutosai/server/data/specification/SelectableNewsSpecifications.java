package hokutosai.server.data.specification;

import hokutosai.server.data.entity.news.SelectableNews;

import org.springframework.data.jpa.domain.Specification;

public class SelectableNewsSpecifications {

	public Specification<SelectableNews> filterByEventId(Integer eventId) {
		return eventId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("eventId"), eventId);
        };
    }

	public Specification<SelectableNews> filterByShopId(Integer shopId) {
		return shopId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("shopId"), shopId);
        };
    }

	public Specification<SelectableNews> filterByExhibitionId(Integer exhibitionId) {
		return exhibitionId == null ? null : (root, query, cb) -> {
            return cb.equal(root.get("exhibitionId"), exhibitionId);
        };
    }

}
