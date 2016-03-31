package hokutosai.server.data.document.log;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;

public class HttpStatus {

	@Getter
	@Field("code")
	private int code;

	@Getter
	@Field("cause")
	private String cause;

	public HttpStatus(org.springframework.http.HttpStatus httpStatus) {
		this.code = httpStatus.value();
		this.cause = httpStatus.getReasonPhrase();
	}

}
