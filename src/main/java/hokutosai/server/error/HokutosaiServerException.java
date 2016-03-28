package hokutosai.server.error;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class HokutosaiServerException extends Exception {

	@Getter
	protected HttpStatus httpStatus;

	public HokutosaiServerException(HttpStatus httpStatus) {
		super(httpStatus.toString());
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(HttpStatus httpStatus, Throwable nested) {
		super(httpStatus.toString(), nested);
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(HttpStatus httpStatus, String message, Throwable nested) {
		super(message, nested);
		this.httpStatus = httpStatus;
	}

}
