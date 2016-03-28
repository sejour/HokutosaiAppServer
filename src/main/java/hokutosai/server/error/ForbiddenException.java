package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ForbiddenException extends HokutosaiServerException {

	public ForbiddenException(String httpMethod, String uri) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN);
	}

	public ForbiddenException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN, message);
	}

	public ForbiddenException(String httpMethod, String uri, Throwable nested) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN, nested);
	}

	public ForbiddenException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.FORBIDDEN, message, nested);
	}

}
