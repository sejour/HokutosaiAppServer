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

	public static Specification<SelectableNews> filterByTopic(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("topic")) ? null : (root, query, cb) -> {
			return cb.isTrue(root.get("isTopic"));
		};
	}

	public static Specification<SelectableNews> filterByOnlyEvents(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("events")) ? null : (root, query, cb) -> {
			return cb.isNotNull(root.get("relatedEvent"));
		};
	}

	public static Specification<SelectableNews> filterByExceptEvents(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("mainly")) ? null : (root, query, cb) -> {
			return cb.isNull(root.get("relatedEvent"));
		};
	}

	public static Specification<SelectableNews> filterByOnlyShops(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("shops")) ? null : (root, query, cb) -> {
			return cb.isNotNull(root.get("relatedShop"));
		};
	}

	public static Specification<SelectableNews> filterByExceptShops(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("mainly")) ? null : (root, query, cb) -> {
			return cb.isNull(root.get("relatedShop"));
		};
	}

	public static Specification<SelectableNews> filterByOnlyExhibitions(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("exhibitions")) ? null : (root, query, cb) -> {
			return cb.isNotNull(root.get("relatedExhibition"));
		};
	}

	public static Specification<SelectableNews> filterByExceptExhibitions(String filter) {
		return (filter == null || !filter.equalsIgnoreCase("mainly")) ? null : (root, query, cb) -> {
			return cb.isNull(root.get("relatedExhibition"));
		};
	}

}
