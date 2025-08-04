package com.jsp.CourseEnrollmentSystem.exception;


// handling scenarios where a requested ID is not found
public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		 // Returns a custom message when this exception is thrown
		return "Id not Found";
	}

}
