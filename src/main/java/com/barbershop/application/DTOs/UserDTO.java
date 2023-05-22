package com.barbershop.application.DTOs;

import java.util.Date;

import com.barbershop.application.formatters.CpfFormatter;

public class UserDTO {
	public Long id;
	public String username;
	public String cpf;
	public String email;
	public String password;
	public String phoneNumber;
	public Date birth;
	public Boolean active;
	public String role;

	public UserDTO() {
	}

	public UserDTO(Long id, 
			String username, 
			String cpf, 
			String email, 
			String phoneNumber,
			Date birth, 
			Boolean active, 
			String role) {
		this.id = id;
		this.username = username;
		this.cpf = CpfFormatter.format(cpf);
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birth = birth;
		this.active = active;
		this.role = role;
	}
}
