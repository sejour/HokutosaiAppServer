package hokutosai.server.error;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class HokutosaiServerException extends Exception {

	@Getter
	protected String httpMethod;

	@Getter
	protected String uri;

	@Getter
	protected HttpStatus httpStatus;

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus) {
		super(String.format("[%s %s] (%s)", httpMethod, uri, httpStatus.toString()));
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus, Throwable nested) {
		super(String.format("[%s %s] (%s)", httpMethod, uri, httpStatus.toString()), nested);
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus, String message) {
		super(String.format("[%s %s] (%s) %s", httpMethod, uri, httpStatus.toString(), message));
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus, String message, Throwable nested) {
		super(String.format("[%s %s] (%s) %s", httpMethod, uri, httpStatus.toString(), message), nested);
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

}
