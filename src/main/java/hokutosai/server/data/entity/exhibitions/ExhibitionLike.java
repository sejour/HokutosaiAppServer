package hokutosai.server.data.entity.exhibitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import hokutosai.server.data.entity.Like;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibitions_likes")
public class ExhibitionLike  extends Like{

	@Column(name = "exhibition_id")
	@Getter @Setter
	private Integer exhibitionId;

	public ExhibitionLike() {}

	public ExhibitionLike(Integer exhibitionId, String accountId) {
		super(accountId);
		this.exhibitionId = exhibitionId;
	}
}
