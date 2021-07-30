package com.csvreader.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus
@RestController
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleDefaultError(Exception ex,WebRequest r){
		ErrorMessage err=new ErrorMessage();
		err.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
