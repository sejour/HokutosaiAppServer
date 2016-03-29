package hokutosai.server.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	@Getter
	@JsonProperty("code")
	private int statusCode;

	@Getter
	@JsonProperty("cause")
	private String cause;

	@Getter
	@JsonProperty("message")
	private String message;

	public ErrorResponse(HttpStatus status, String message) {
		this.statusCode = status.value();
		this.cause = status.getReasonPhrase();
		this.message = message;
	}

}
