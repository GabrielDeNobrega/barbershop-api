package com.barbershop.application.core.base.classes;

import org.springframework.http.HttpStatus;

public class ErrorMessage{
	
	private String message;
	private HttpStatus statusCodeName;
	private int statusCode;
	
	public ErrorMessage(String message, HttpStatus statusCodeName, int statusCode) {
		super();
		this.message = message;
		this.statusCodeName = statusCodeName;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatusCodeName() {
		return statusCodeName;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
