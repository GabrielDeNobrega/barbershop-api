package com.barbershop.application.DTOs;

import java.util.Date;

import com.barbershop.application.enums.Role;

public class UserDTO {
	public Long id;
	public String name;
	public String cpf;
	public String email;
	public Date birth;
	public Boolean active;
	public Role role;
	
	public UserDTO(Long id, String name, String cpf, String email, Date birth, Boolean active, Role role) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.birth = birth;
		this.active = active;
		this.role = role;
	}
}
