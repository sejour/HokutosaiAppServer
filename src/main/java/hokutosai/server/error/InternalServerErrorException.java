package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class InternalServerErrorException extends HokutosaiServerException {

	public InternalServerErrorException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

	public InternalServerErrorException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.INTERNAL_SERVER_ERROR, message, nested);
	}

}
