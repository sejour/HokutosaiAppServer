package hokutosai.server.error.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class ErrorResponse {

	@Getter
	@JsonProperty("error")
	private Error error;

	@Getter
	@JsonIgnore
	private HttpStatus httpStatus;

	public ErrorResponse(HttpStatus status, String message) {
		this.error = new Error(status, message);
		this.httpStatus = status;
	}

}
