package com.barbershop.application.enums;

import java.util.Arrays;

public enum KindOfCare {
	BEARD("Beard"),
	HAIR("Hair"),
	BEARD_HAIR("Beard and Hair"),
	UNDEFINED("undefined");
	
	private String value;
	
	private KindOfCare(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static KindOfCare get(String role){
		  return Arrays.stream(KindOfCare.values())
		            .filter(env -> env.value.equalsIgnoreCase(role))
		            .findFirst().orElse(UNDEFINED);
	}
}
