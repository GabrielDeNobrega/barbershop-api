package com.barbershop.application.formatters;

public class CpfFormatter {
	
	public static String format(String cpf) {
		return cpf.replaceAll("[^0-9]", "");  
	}
}
