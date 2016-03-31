package hokutosai.server.data.document.log;

import hokutosai.server.data.document.auth.AuthorizationApiUser;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;

@Document(collection = "ErrorLogs")
public class AccessErrorLog extends AccessLog {

	@Getter
	@Field("error")
	private ExceptionError exception;

	public AccessErrorLog(HttpServletRequest request, AuthorizationApiUser apiUser, org.springframework.http.HttpStatus httpStatus, Throwable e) {
		super(request, apiUser, httpStatus);
		this.exception = new ExceptionError(e);
	}

}
