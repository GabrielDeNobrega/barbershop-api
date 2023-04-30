package com.barbershop.application;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.barbershop.application.core.base.classes.ErrorMessage;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CustomApplicationException.class })
	protected ResponseEntity<Object> handleBadRequest(CustomApplicationException ex, WebRequest request) {
		return handleExceptionInternal(ex, mountErrorMessage(ex), new HttpHeaders(), ex.getHttpStatus(), request);
	}
	
	private String mountErrorMessage(CustomApplicationException ex) {
		try {
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ex.getHttpStatus(), ex.getHttpStatus().value());
			return new ObjectMapper().writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{\"message\":\"unable to convert error into a json string\","
					+ "\"statusCode\":\"INTERNAL_SERVER_ERROR\","
					+ "\"statusCode\":500}";
		}
	}
}
