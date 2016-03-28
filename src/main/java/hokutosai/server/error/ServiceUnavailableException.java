package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ServiceUnavailableException extends HokutosaiServerException {

	public ServiceUnavailableException(String httpMethod, String uri) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ServiceUnavailableException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE, message);
	}

	public ServiceUnavailableException(String httpMethod, String uri, Throwable nested) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE, nested);
	}

	public ServiceUnavailableException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE, message, nested);
	}

}
