package com.barbershop.application.enums;

import java.util.Arrays;

public enum Role{
	ADMINISTRATOR("Administrator"), 
	EMPLOYEE("Employee"), 
	CUSTOMER("Customer"),
	UNDEFINED("Undefined");
	
	private String value;
	
	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static Role get(String role){
		  return Arrays.stream(Role.values())
		            .filter(env -> env.value.equalsIgnoreCase(role))
		            .findFirst().orElse(UNDEFINED);
	}
}
