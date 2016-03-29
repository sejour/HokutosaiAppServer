package hokutosai.server.error;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ServiceUnavailableException extends HokutosaiServerException {

	public ServiceUnavailableException(String message) {
		super(HttpStatus.SERVICE_UNAVAILABLE, message);
	}

	public ServiceUnavailableException(String message, Throwable nested) {
		super(HttpStatus.SERVICE_UNAVAILABLE, message, nested);
	}

}
