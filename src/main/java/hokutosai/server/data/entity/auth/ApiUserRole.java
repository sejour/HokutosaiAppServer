package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_users_roles")
public class ApiUserRole extends Permission {

	@Id
	@Column(name = "role")
	@Getter @Setter
	private String role;

}
