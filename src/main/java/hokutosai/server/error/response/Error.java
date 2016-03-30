package hokutosai.server.error.response;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

	@Getter
	@JsonProperty("code")
	private int statusCode;

	@Getter
	@JsonProperty("cause")
	private String cause;

	@Getter
	@JsonProperty("message")
	private String message;

	public Error(HttpStatus status, String message) {
		this.statusCode = status.value();
		this.cause = status.getReasonPhrase();
		this.message = message;
	}

}
