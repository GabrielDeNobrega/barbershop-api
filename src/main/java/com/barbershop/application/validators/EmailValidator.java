package com.barbershop.application.validators;

import java.util.regex.Pattern;

public class EmailValidator {
	
	public static Boolean isValid(String email) {
		return Pattern
				.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
				.matcher(email)
				.matches();
	}
}
