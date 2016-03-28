package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UnauthorizedException extends HokutosaiServerException {

	public UnauthorizedException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.UNAUTHORIZED, message);
	}

	public UnauthorizedException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.UNAUTHORIZED, message, nested);
	}

}
