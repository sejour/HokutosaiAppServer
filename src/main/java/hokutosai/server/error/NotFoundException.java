package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundException extends HokutosaiServerException {

	public NotFoundException(String httpMethod, String uri) {
		super(httpMethod, uri, HttpStatus.NOT_FOUND);
	}

	public NotFoundException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.NOT_FOUND, message);
	}

	public NotFoundException(String httpMethod, String uri, Throwable nested) {
		super(httpMethod, uri, HttpStatus.NOT_FOUND, nested);
	}

	public NotFoundException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.NOT_FOUND, message, nested);
	}

}
