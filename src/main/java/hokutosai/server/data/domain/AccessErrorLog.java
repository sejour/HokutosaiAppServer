package hokutosai.server.data.domain;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

public class AccessErrorLog extends AccessLog {

	@Getter
	private ExceptionError exception;

	public AccessErrorLog(HttpServletRequest request, AuthorizationApiUser apiUser, org.springframework.http.HttpStatus httpStatus, Throwable e) {
		super(request, apiUser, httpStatus);
		this.exception = new ExceptionError(e);
	}

}
