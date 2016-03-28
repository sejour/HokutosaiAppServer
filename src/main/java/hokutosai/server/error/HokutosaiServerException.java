package hokutosai.server.error;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class HokutosaiServerException extends Exception {

	@Getter
	protected String uri;

	@Getter
	protected HttpStatus httpStatus;

	public HokutosaiServerException(String uri, HttpStatus httpStatus) {
		super(String.format("[%s] (%s)", uri, httpStatus.toString()));
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String uri, HttpStatus httpStatus, Throwable nested) {
		super(String.format("[%s] (%s)", uri, httpStatus.toString()), nested);
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String uri, HttpStatus httpStatus, String message) {
		super(String.format("[%s] (%s) %s", uri, httpStatus.toString(), message));
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String uri, HttpStatus httpStatus, String message, Throwable nested) {
		super(String.format("[%s] (%s) %s", uri, httpStatus.toString(), message), nested);
		this.httpStatus = httpStatus;
	}

}
