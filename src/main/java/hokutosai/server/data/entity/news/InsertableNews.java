package hokutosai.server.data.entity.news;

import hokutosai.server.data.entity.media.Media;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "news")
public class InsertableNews extends News {

	@Transient
	@JsonProperty("medias")
	@Getter @Setter
	private List<Media> medias;

}
