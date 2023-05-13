package com.barbershop.application.DTOs;

import java.util.Date;

public class UserDTO {
	public Long id;
	public String username;
	public String cpf;
	public String email;
	public String password;
	public Date birth;
	public Boolean active;
	public String role;
	
	public UserDTO(Long id, String username, String cpf, String email, Date birth, Boolean active, String role) {
		this.id = id;
		this.username = username;
		this.cpf = cpf;
		this.email = email;
		this.birth = birth;
		this.active = active;
		this.role = role;
	}
}
