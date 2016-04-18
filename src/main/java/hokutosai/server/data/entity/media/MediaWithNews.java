package hokutosai.server.data.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medias")
public class MediaWithNews extends SuperMedia {

	@Column(name = "news_id")
	@JsonIgnore
	@Getter @Setter
	private Integer newsId;

	@Column(name = "sequence")
	@Getter @Setter
	private Integer sequence;

}
