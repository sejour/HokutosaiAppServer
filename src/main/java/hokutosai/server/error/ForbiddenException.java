package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ForbiddenException extends HokutosaiServerException {

	public ForbiddenException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN, message);
	}

	public ForbiddenException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN, message, nested);
	}

}
