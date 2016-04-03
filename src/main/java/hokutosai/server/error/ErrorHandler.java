package hokutosai.server.error;

import javax.servlet.ServletRequest;

import hokutosai.server.error.response.ErrorResponse;

import org.springframework.http.HttpStatus;

public class ErrorHandler {

	public static ErrorResponse handle(Throwable threw, ServletRequest request) {
		if (threw == null) { return null; }

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		for (Throwable e = threw; e != null; e = e.getCause()) {
			if (e instanceof HokutosaiServerException) {
				HokutosaiServerException hse = (HokutosaiServerException)e;
				status = hse.getHttpStatus();
				threw = e;
				break;
			}
		}

		request.setAttribute("Error", threw);

		return new ErrorResponse(status, status.is5xxServerError() ? null : threw.getMessage());
	}

}
