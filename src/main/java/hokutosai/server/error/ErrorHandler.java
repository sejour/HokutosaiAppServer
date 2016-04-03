package hokutosai.server.error;

import javax.servlet.ServletRequest;

import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.security.auth.AuthorizationAccountException;
import hokutosai.server.security.auth.AuthorizationApiUserException;

import org.springframework.http.HttpStatus;

public class ErrorHandler {

	public static ErrorResponse handle(Throwable threw, ServletRequest request) {
		if (threw == null) { return null; }

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		for (Throwable e = threw; e != null; e = e.getCause()) {
			if (e instanceof HokutosaiServerException) {
				HokutosaiServerException hse = (HokutosaiServerException)e;
				status = hse.getHttpStatus();
				if (hse instanceof AuthorizationApiUserException) request.setAttribute("ApiUser", ((AuthorizationApiUserException)hse).getApiUser());
				else if (hse instanceof AuthorizationAccountException) request.setAttribute("Account", ((AuthorizationAccountException)hse).getAccount());
				threw = e;
				break;
			}
		}

		request.setAttribute("Error", threw);

		return new ErrorResponse(status, status.is5xxServerError() ? null : threw.getMessage());
	}

}
