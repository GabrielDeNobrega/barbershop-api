package com.barbershop.application.enums;

import java.util.Arrays;

public enum AppointmentStatus{
	SCHEDULED("Scheduled"), 
	CANCELED("Canceled"), 
	FINISHED("Finished"),
	UNDEFINED("undefined");
	
	private String value;
	
	private AppointmentStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static AppointmentStatus get(String appointmentStatus){
		  return Arrays.stream(AppointmentStatus.values())
		            .filter(env -> env.value.equalsIgnoreCase(appointmentStatus))
		            .findFirst().orElse(UNDEFINED);
	}
}