package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Like {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "account_id")
	private String accountId;

}
