package hokutosai.server.data.entity.news;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import hokutosai.server.data.entity.events.EventItem;
import hokutosai.server.data.entity.exhibitions.ExhibitionItem;
import hokutosai.server.data.entity.media.Media;
import hokutosai.server.data.entity.shops.ShopItem;
import hokutosai.server.data.json.news.Topic;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
public class TopicNews extends News implements Topic {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id", insertable = false, updatable = false)
	@JsonProperty("related_event")
	@Getter @Setter
	private EventItem relatedEvent;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shop_id", insertable = false, updatable = false)
	@JsonProperty("related_shop")
	@Getter @Setter
	private ShopItem relatedShop;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exhibition_id", insertable = false, updatable = false)
	@JsonProperty("related_exhibition")
	@Getter @Setter
	private ExhibitionItem relatedExhibition;

	@Column(name = "likes_count", insertable = false)
	@JsonProperty("likes_count")
	@Getter @Setter
	private Integer likesCount;

	@Transient
	@JsonProperty("liked")
	@Getter @Setter
	private Boolean liked;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "news_id", insertable = false, updatable = false)
	@OrderBy("sequence ASC")
	@JsonProperty("medias")
	@Getter @Setter
	private List<Media> medias;

	@Transient
	public String getMediaUrl() {
		if (this.medias != null && !this.medias.isEmpty()) {
			return this.medias.get(0).getUrl();
		}
		return null;
	}

}
