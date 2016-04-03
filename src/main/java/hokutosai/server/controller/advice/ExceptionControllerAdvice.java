package hokutosai.server.controller.advice;

import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.error.ErrorHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Throwable.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, Throwable e) {
		ErrorResponse error = ErrorHandler.handle(e, request);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
