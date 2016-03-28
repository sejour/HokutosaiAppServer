package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundException extends HokutosaiServerException {

	public NotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public NotFoundException(Throwable nested) {
		super(HttpStatus.NOT_FOUND, nested);
	}

	public NotFoundException(String message, Throwable nested) {
		super(HttpStatus.NOT_FOUND, message, nested);
	}

}
