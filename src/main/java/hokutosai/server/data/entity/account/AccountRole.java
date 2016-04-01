package hokutosai.server.data.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "accounts_roles")
@Data
public class AccountRole {

	@Id
	@Column(name = "role")
	private String role;

	@Column(name = "permission")
	private String permission;

}
