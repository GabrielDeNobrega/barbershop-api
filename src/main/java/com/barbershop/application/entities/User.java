package com.barbershop.application.entities;

import java.util.Date;
import java.util.List;

import com.barbershop.application.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private Date birth;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToMany(mappedBy = "customer")
	private List<Appointment> appointments;

	public User() {
	}

	public User(Long id, String name, String cpf, Date birth, String email, String password, Role role,
			List<Appointment> appointments) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birth = birth;
		this.email = email;
		this.password = password;
		this.role = role;
		this.appointments = appointments;
	}

}
