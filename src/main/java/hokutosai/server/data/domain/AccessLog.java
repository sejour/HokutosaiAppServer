package hokutosai.server.data.domain;

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
	private AuthorizationApiUser apiUser;

	@Field("status")
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
