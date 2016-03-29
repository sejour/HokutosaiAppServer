package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	//@Column(name = "role")
	//private String role;

	@Column(name = "permission")
	private String permission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	private ApiUserRole role;

}
