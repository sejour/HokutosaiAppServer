package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UnauthorizedException extends HokutosaiServerException {

	public UnauthorizedException() {
		super(HttpStatus.UNAUTHORIZED);
	}

	public UnauthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}

	public UnauthorizedException(Throwable nested) {
		super(HttpStatus.UNAUTHORIZED, nested);
	}

	public UnauthorizedException(String message, Throwable nested) {
		super(HttpStatus.UNAUTHORIZED, message, nested);
	}

}
