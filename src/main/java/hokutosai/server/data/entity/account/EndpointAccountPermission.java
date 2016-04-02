package hokutosai.server.data.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import hokutosai.server.data.entity.EndpointPermission;

@Entity
@Table(name = "endpoints_accounts_permissions")
public class EndpointAccountPermission extends EndpointPermission {

	@Column(name = "account_role")
	@Getter @Setter
	private String role;

}
