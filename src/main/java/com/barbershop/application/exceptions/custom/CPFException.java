package com.barbershop.application.exceptions.custom;

public class CPFException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CPFException(String errorMessage) {
		super(errorMessage);
	}
}
