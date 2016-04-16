package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.media.MediaWithNews;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "news")
public class SelectableNews extends News {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "news_id", insertable = false, updatable = false)
	@OrderBy("sequence ASC")
	@JsonProperty("medias")
	@Getter @Setter
	private List<MediaWithNews> medias;

	@Transient
	@JsonProperty("liked")
	@Getter @Setter
	private boolean liked;

}
