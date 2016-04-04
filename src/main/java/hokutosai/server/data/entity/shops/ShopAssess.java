package hokutosai.server.data.entity.shops;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hokutosai.server.data.entity.Assess;

@Entity
@Table(name = "shops_assesses")
public class ShopAssess extends Assess {

	@Column(name = "shop_id")
	@JsonIgnore
	@Getter @Setter
	private Integer shopId;

	public ShopAssess() {}

	public ShopAssess(Integer shopId, String accountId, Date datetime, Integer score, String comment) {
		super(accountId, datetime, score, comment);
		this.shopId = shopId;
	}

}
