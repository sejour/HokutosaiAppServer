package hokutosai.server.data.domain;

import lombok.Getter;

public class ExceptionError {

	@Getter
	private String name;

	@Getter
	private String message;

	public ExceptionError(Throwable e) {
		this.name = e.getClass().getName();
		this.message = e.getMessage();
	}

}
