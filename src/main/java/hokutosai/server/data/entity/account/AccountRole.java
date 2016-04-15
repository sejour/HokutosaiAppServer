package hokutosai.server.data.entity.account;

import hokutosai.server.data.entity.auth.Permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts_roles")
public class AccountRole extends Permission {

	@Id
	@Column(name = "role")
	@Getter @Setter
	private String role;
	
	public AccountRole() {}
	
	public AccountRole(String role, String permission) {
		super(permission);
		this.role = role;
	}

}
