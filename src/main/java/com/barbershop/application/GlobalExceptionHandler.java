package com.barbershop.application;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.barbershop.application.core.base.classes.ErrorMessage;
import com.barbershop.application.exceptions.custom.CustomApplicationException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CustomApplicationException.class })
	protected ResponseEntity<Object> handleBadRequest(CustomApplicationException ex, WebRequest request) {
		return handleExceptionInternal(ex, mountErrorMessage(ex), new HttpHeaders(), ex.getHttpStatus(), request);
	}

	
	private ErrorMessage mountErrorMessage(CustomApplicationException ex) {
		return new ErrorMessage(ex.getMessage(), ex.getHttpStatus(), ex.getHttpStatus().value());
	}
}
