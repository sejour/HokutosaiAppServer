package hokutosai.server.data.domain;

import lombok.Data;

@Data
public class AuthorizationApiUser {

	private String role;

	private String userId;

	public AuthorizationApiUser(String role, String userId) {
		this.role = role;
		this.userId = userId;
	}

}
