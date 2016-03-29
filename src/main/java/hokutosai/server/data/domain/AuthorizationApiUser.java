package hokutosai.server.data.domain;

import lombok.Data;

@Data
public class AuthorizationApiUser {

	private String role;

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
