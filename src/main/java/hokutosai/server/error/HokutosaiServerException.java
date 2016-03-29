package hokutosai.server.error;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class HokutosaiServerException extends Exception {

	@Getter
	protected HttpStatus httpStatus;

	public HokutosaiServerException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HokutosaiServerException(HttpStatus httpStatus, String message, Throwable nested) {
		super(message, nested);
		this.httpStatus = httpStatus;
	}

	public String getLogMessage() {
		return String.format("(%s:%s) %s", this.httpStatus.toString(), this.httpStatus.getReasonPhrase(), this.getMessage());
	}

}
