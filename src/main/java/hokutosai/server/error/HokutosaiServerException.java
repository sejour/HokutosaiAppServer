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

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus, String message) {
		super(message);
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(String httpMethod, String uri, HttpStatus httpStatus, String message, Throwable nested) {
		super(message, nested);
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.httpStatus = httpStatus;
	}

	public String getLogMessage() {
		return String.format("[%s %s] (%s:%s) %s", this.httpMethod, this.uri, this.httpStatus.toString(), this.httpStatus.getReasonPhrase(), this.getMessage());
	}

}
