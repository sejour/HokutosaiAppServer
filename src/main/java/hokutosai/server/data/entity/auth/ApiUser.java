package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_users")
public class ApiUser extends Permission {

	@Id
	@Column(name = "user_id")
	@Getter @Setter
	private String userId;

	@Column(name = "access_token")
	@Getter @Setter
	private String accessToken;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	@Getter @Setter
	private ApiUserRole role;

}
