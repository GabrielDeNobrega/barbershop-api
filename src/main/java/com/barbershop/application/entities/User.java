package com.barbershop.application.entities;

import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.barbershop.application.core.base.classes.BaseEntity;
import com.barbershop.application.enums.Role;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.validators.CPFValidator;

@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

	private String name;
	private String cpf;
	private String email;

	@SuppressWarnings("unused")
	private String password;
	private Date birth;
	private Boolean active;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany
	private List<Appointment> appointments;

	public User() {
	}

	public User(String name, String cpf, String email, Date birth, Boolean active, Role role) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.birth = birth;
		this.active = active;
		this.role = role;
		validate();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirth() {
		return birth;
	}

	public Boolean getActive() {
		return active;
	}

	public Role getRole() {
		return role;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	@Override
	public void validate() {
		if (!CPFValidator.isValid(cpf)) {
			throw new CustomApplicationException("Invalid CPF", HttpStatus.BAD_REQUEST);
		}
		
		if(role.equals(Role.UNDEFINED)) {
			throw new CustomApplicationException("Select a Valid Role", HttpStatus.BAD_REQUEST);
		}
	}
}
