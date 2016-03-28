package hokutosai.server.security.auth;

import hokutosai.server.error.ForbiddenException;

@SuppressWarnings("serial")
public class ApiUserForbiddenException extends ForbiddenException {

	public ApiUserForbiddenException() {
		super();
	}

	public ApiUserForbiddenException(String message) {
		super(message);
	}

	public ApiUserForbiddenException(Throwable nested) {
		super(nested);
	}

	public ApiUserForbiddenException(String message, Throwable nested) {
		super(message, nested);
	}

}
