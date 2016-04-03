package hokutosai.server.data.document.log;

import hokutosai.server.data.document.auth.AuthorizationTarget;

import java.util.Date;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;

@Document(collection = "Logs")
public class AccessLog {

	@Id
	private String id;

	@Field("datetime")
	@Getter
	private Date date;

	@Field("uri")
	@Getter
	private String uri;

	@Field("method")
	@Getter
	private String method;

	@Field("apiUser")
	@Getter
	private AuthorizationTarget apiUser;

	@Field("account")
	@Getter
	private AuthorizationTarget account;

	@Field("status")
	@Getter
	private HttpStatus status;

	public AccessLog(HttpServletRequest request, AuthorizationTarget apiUser, org.springframework.http.HttpStatus httpStatus) {
		this.date = new Date();
		this.uri = request.getRequestURI();
		this.method = request.getMethod();
		this.apiUser = apiUser;
		this.status = new HttpStatus(httpStatus);
	}

	public AccessLog(HttpServletRequest request, AuthorizationTarget apiUser, AuthorizationTarget account, org.springframework.http.HttpStatus httpStatus) {
		this.date = new Date();
		this.uri = request.getRequestURI();
		this.method = request.getMethod();
		this.apiUser = apiUser;
		this.account = account;
		this.status = new HttpStatus(httpStatus);
	}

}
