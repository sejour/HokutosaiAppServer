package hokutosai.server.data.entity.auth;

import hokutosai.server.data.entity.EndpointPermission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endpoints_api_users_permissions")
public class EndpointApiUserPermission extends EndpointPermission {

	@Column(name = "api_user_role")
	@Getter @Setter
	private String role;

}

