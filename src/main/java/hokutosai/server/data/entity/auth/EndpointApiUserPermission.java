package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endpoints_api_users_permissions")
public class EndpointApiUserPermission extends Permission {

	@Id
	@Column(name = "id")
	@Getter @Setter
	private Integer id;

	@Id
	@Column(name = "path")
	@Getter @Setter
	private String path;

	@Id
	@Column(name = "method")
	@Getter @Setter
	private String method;

	@Id
	@Column(name = "api_user_role")
	@Getter @Setter
	private String role;

	@Id
	@Column(name = "permission")
	@Getter @Setter
	private String permission;

}

