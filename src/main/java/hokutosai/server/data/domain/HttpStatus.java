package hokutosai.server.data.domain;

import lombok.Getter;

public class HttpStatus {

	@Getter
	private int code;

	@Getter
	private String cause;

	public HttpStatus(org.springframework.http.HttpStatus httpStatus) {
		this.code = httpStatus.value();
		this.cause = httpStatus.getReasonPhrase();
	}

}
