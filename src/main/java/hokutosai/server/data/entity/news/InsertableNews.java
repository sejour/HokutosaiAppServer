package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.media.Media;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "news")
public class InsertableNews extends News {

	@Column(name = "event_id")
	@JsonProperty("event_id")
	@Getter @Setter
	private Integer eventId;

	@Column(name = "shop_id")
	@JsonProperty("shop_id")
	@Getter @Setter
	private Integer shopId;

	@Column(name = "exhibition_id")
	@JsonProperty("exhibition_id")
	@Getter @Setter
	private Integer exhibitionId;

	@Transient
	@JsonProperty("medias")
	@Getter @Setter
	private List<Media> medias;

}
