package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "api_users")
@Data
public class ApiUser {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "access_token")
	private String accessToken;

	@Column(name = "role")
	private String role;

	@Column(name = "permission")
	private String permission;

}
