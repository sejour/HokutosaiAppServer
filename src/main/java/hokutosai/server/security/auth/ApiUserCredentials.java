package hokutosai.server.security.auth;

import lombok.Getter;

public class ApiUserCredentials {

	@Getter
	private String id;

	@Getter
	private String accessToken;

	public ApiUserCredentials(String id, String accessToken) {
		this.id = id;
		this.accessToken = accessToken;
	}

}
