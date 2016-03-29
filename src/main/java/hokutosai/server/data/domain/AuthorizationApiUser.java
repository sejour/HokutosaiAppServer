package hokutosai.server.data.domain;

import lombok.Getter;

public class AuthorizationApiUser {

	@Getter
	private String role;

	@Getter
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
