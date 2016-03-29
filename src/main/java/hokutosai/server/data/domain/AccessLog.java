package hokutosai.server.data.domain;

import java.util.Date;

import lombok.Getter;

public class AccessLog {

	@Getter
	private Date date;

	@Getter
	private String uri;

	@Getter
	private String method;

	@Getter
	private AuthorizationApiUser apiUser;

	@Getter
	private HttpStatus status;

	public AccessLog(String uri, String method, AuthorizationApiUser apiUser, org.springframework.http.HttpStatus httpStatus) {
		this.date = new Date();
		this.uri = uri;
		this.method = method;
		this.apiUser = apiUser;
		this.status = new HttpStatus(httpStatus);
	}

}
