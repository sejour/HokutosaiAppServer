package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ForbiddenException extends HokutosaiServerException {

	public ForbiddenException() {
		super(HttpStatus.FORBIDDEN);
	}

	public ForbiddenException(String message) {
		super(HttpStatus.FORBIDDEN, message);
	}

	public ForbiddenException(Throwable nested) {
		super(HttpStatus.FORBIDDEN, nested);
	}

	public ForbiddenException(String message, Throwable nested) {
		super(HttpStatus.FORBIDDEN, message, nested);
	}

}
