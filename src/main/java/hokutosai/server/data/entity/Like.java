package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Like {

	@Id
	@Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "account_id")
	private String accountId;

	public Like() {}

	public Like(String accountId) {
		this.accountId = accountId;
	}

}
