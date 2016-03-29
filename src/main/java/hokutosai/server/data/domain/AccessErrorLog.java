package hokutosai.server.data.domain;

import lombok.Getter;

public class AccessErrorLog extends AccessLog {

	@Getter
	private ExceptionError exception;

	public AccessErrorLog(String uri, String method, AuthorizationApiUser apiUser, org.springframework.http.HttpStatus httpStatus, Throwable e) {
		super(uri, method, apiUser, httpStatus);
		this.exception = new ExceptionError(e);
	}

}
