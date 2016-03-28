package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BadRequestException extends HokutosaiServerException {

	public BadRequestException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.BAD_REQUEST, message);
	}

	public BadRequestException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.BAD_REQUEST, message, nested);
	}

}
