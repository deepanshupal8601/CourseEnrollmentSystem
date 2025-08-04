package com.jsp.CourseEnrollmentSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.CourseEnrollmentSystem.dto.ResponseStructure;

@RestControllerAdvice    //makes the class a global exception handler for REST controllers.
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)  // handles IdNotFoundException whenever it is thrown from any controller
	public ResponseEntity<ResponseStructure<String>>handlerIdNotFoundException(IdNotFoundException exception){
		// custom response structure to send detailed error information
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Failure");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}

}
