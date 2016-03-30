package hokutosai.server.data.domain.auth;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;

public class AuthorizationApiUser {

	@Getter
	@Field("role")
	private String role;

	@Getter
	@Field("user_id")
	private String userId;

	public AuthorizationApiUser(String userId) {
		this.role = "<<unknown>>";
		this.userId = userId;
	}

	public AuthorizationApiUser(String userId, String role) {
		this.role = role;
		this.userId = userId;
	}

}
