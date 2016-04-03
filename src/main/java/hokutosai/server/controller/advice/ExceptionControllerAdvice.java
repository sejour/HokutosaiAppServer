package hokutosai.server.controller.advice;

import hokutosai.server.error.response.ErrorResponse;
import hokutosai.server.error.ErrorHandler;
import hokutosai.server.error.HokutosaiServerException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.sun.corba.se.impl.io.TypeMismatchException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(HokutosaiServerException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HokutosaiServerException e) {
		ErrorResponse error = ErrorHandler.handle(e, request);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, MissingServletRequestParameterException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, ServletRequestBindingException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, TypeMismatchException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, MethodArgumentNotValidException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HttpMessageNotReadableException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(MissingServletRequestPartException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, MissingServletRequestPartException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(BindException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, BindException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, NoSuchRequestHandlingMethodException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HttpMediaTypeNotAcceptableException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, HttpClientErrorException e) {
		ErrorResponse error = ErrorHandler.handle(e, request, e.getStatusCode());
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

	@ExceptionHandler(Throwable.class)
	@ResponseBody
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, Throwable e) {
		ErrorResponse error = ErrorHandler.handle(e, request);
		return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
