package hokutosai.server.security.auth;

import lombok.Getter;

public class AccountCredentials {

	@Getter
	private String id;

	@Getter
	private String password;

	public AccountCredentials(String id, String password) {
		this.id = id;
		this.password = password;
	}

}
