package hokutosai.server.data.entity.shops;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.data.entity.assessments.Assess;
import hokutosai.server.error.InvalidParameterValueException;

@Entity
@Table(name = "shops_assesses")
public class ShopAssess extends Assess {

	@Column(name = "shop_id")
	@JsonIgnore
	@Getter @Setter
	private Integer shopId;

	public ShopAssess() {}

	public ShopAssess(Integer shopId, SecureAccount account, Date datetime, Integer score, String comment) throws InvalidParameterValueException {
		super(account, datetime, score, comment);
		this.shopId = shopId;
	}

}
