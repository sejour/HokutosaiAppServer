package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ServiceUnavailableException extends HokutosaiServerException {

	public ServiceUnavailableException(String httpMethod, String uri, String message) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE, message);
	}

	public ServiceUnavailableException(String httpMethod, String uri, String message, Throwable nested) {
		super(httpMethod, uri, HttpStatus.SERVICE_UNAVAILABLE, message, nested);
	}

}
