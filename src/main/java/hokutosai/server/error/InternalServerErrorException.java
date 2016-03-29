package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class InternalServerErrorException extends HokutosaiServerException {

	public InternalServerErrorException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

	public InternalServerErrorException(String message, Throwable nested) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message, nested);
	}

}
