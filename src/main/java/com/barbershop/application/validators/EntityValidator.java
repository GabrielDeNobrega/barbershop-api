package com.barbershop.application.validators;

public class EntityValidator{
	
	public static boolean isNullOrEmpty( String value) {
		return value == null 
				|| value.isEmpty() 
				|| value.trim().isEmpty();
	}
}
