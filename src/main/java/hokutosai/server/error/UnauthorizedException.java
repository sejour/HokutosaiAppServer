package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UnauthorizedException extends HokutosaiServerException {

	public UnauthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}

	public UnauthorizedException(String message, Throwable nested) {
		super(HttpStatus.UNAUTHORIZED, message, nested);
	}

}
