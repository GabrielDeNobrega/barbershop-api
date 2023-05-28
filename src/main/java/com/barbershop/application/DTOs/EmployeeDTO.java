package com.barbershop.application.DTOs;

public class EmployeeDTO {
	public Long id;
	public String username;
	public String email;

	public EmployeeDTO() {
	}

	public EmployeeDTO(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
}
