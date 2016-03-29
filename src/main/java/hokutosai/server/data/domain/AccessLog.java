package hokutosai.server.data.domain;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

	public AccessLog(HttpServletRequest request, AuthorizationApiUser apiUser, org.springframework.http.HttpStatus httpStatus) {
		this.date = new Date();
		this.uri = request.getRequestURI();
		this.method = request.getMethod();
		this.apiUser = apiUser;
		this.status = new HttpStatus(httpStatus);
	}

}
