package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BadRequestException extends HokutosaiServerException {

	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	public BadRequestException(Throwable nested) {
		super(HttpStatus.BAD_REQUEST, nested);
	}

	public BadRequestException(String message, Throwable nested) {
		super(HttpStatus.BAD_REQUEST, message, nested);
	}

}
