package hokutosai.server.security.auth;

import hokutosai.server.error.UnauthorizedException;

@SuppressWarnings("serial")
public class ApiUserUnauthorizedException extends UnauthorizedException {

	public ApiUserUnauthorizedException() {
		super();
	}

	public ApiUserUnauthorizedException(String message) {
		super(message);
	}

	public ApiUserUnauthorizedException(Throwable nested) {
		super(nested);
	}

	public ApiUserUnauthorizedException(String message, Throwable nested) {
		super(message, nested);
	}

}
