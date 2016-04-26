package hokutosai.server.data.entity.exhibitions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.error.InvalidParameterValueException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibitions_assesses")
public class ExhibitionAssess extends Assess{

	@Column(name = "exhibition_id")
	@JsonIgnore
	@Getter @Setter
	private Integer exhibitionId;

	public ExhibitionAssess() {}

	public ExhibitionAssess(Integer exhibitionId, SecureAccount account, Date datetime, Integer score, String comment) throws InvalidParameterValueException {
		super(account, datetime, score, comment);
		this.exhibitionId = exhibitionId;
	}
}
