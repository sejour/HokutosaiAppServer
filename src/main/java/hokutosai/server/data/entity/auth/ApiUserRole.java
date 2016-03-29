package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "api_users_roles")
@Data
public class ApiUserRole {

	@Id
	@Column(name = "role")
	private String role;

	@Column(name = "permission")
	private String permission;

}
