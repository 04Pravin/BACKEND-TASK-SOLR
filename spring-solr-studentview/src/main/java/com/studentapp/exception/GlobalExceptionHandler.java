package com.studentapp.exception;

import java.time.LocalDateTime;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.studentapp.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		String error = "Request Method not supported";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status, status.value(), error);
		headers.add("info", message);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String error = "Media type not supported";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status,status.value(), error);
		headers.add("info", message);
		
		return ResponseEntity.status(status).headers(headers).body(errors);
		
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String error = "Missing path variable";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status,status.value(), error);
		headers.add("info", message);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String error = "Missing request parameter";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status,status.value(), error);
		headers.add("info", message);
		
		return ResponseEntity.status(status).headers(headers).body(errors);
	
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String error = "Type mismatch";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status,status.value(), error);
		headers.add("info", message);
		
		return ResponseEntity.status(status).headers(headers).body(errors);

	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFound(StudentNotFoundException ex){
		
		String message = ex.getMessage();
		String error = "Student Not Found Exception";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, HttpStatus.GONE,HttpStatus.GONE.value(), error);
		HttpHeaders headers = new HttpHeaders();
		headers.add("info", message);
		
		return ResponseEntity.status(HttpStatus.GONE).headers(headers).body(errors);
		
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> idNotFound(IdNotFoundException ex){
		
		String message = ex.getMessage();
		String error = "Id Not Found Exception";
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, HttpStatus.GONE,HttpStatus.GONE.value(), error);
		HttpHeaders headers = new HttpHeaders();
		headers.add("info", message);
		
		return ResponseEntity.status(HttpStatus.GONE).headers(headers).body(errors);
		
	}


}
