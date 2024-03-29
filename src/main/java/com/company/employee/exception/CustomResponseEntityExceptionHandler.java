package com.company.employee.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author Gaurav
 * @see Custom Exception Handler
 * @since February, 2022
 *
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(Exception exception, WebRequest request) {
		exception.printStackTrace();
		var response = new ExceptionResponse();
		response.setMessage(exception.toString());
		response.setDetails(request.getDescription(false));
		response.setDate(new Date());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handelAllException(Exception exception, WebRequest request) {
		exception.printStackTrace();
		var response = new ExceptionResponse();
		response.setMessage(exception.getMessage());
		response.setDetails(request.getDescription(false));
		response.setDate(new Date());

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.printStackTrace();
		var response = new ExceptionResponse();
		response.setMessage(ex.getMessage());
		response.setDetails(request.getDescription(false));
		response.setDate(new Date());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
