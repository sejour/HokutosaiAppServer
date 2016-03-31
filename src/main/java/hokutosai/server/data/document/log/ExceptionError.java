package hokutosai.server.data.document.log;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;

public class ExceptionError {

	@Getter
	@Field("exception")
	private String name;

	@Getter
	@Field("message")
	private String message;

	public ExceptionError(Throwable e) {
		this.name = e.getClass().getName();
		this.message = e.getMessage();
	}

}
