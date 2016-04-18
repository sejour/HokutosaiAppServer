package hokutosai.server.data.entity.media;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medias")
public class Media extends SuperMedia {

	public Media() {}

	public Media(String type) {
		super(type);
	}

}
