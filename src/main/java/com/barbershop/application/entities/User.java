package com.barbershop.application.entities;

import java.util.Date;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.barbershop.application.core.base.classes.BaseEntity;
import com.barbershop.application.enums.Role;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.formatters.CpfFormatter;
import com.barbershop.application.validators.CPFValidator;
import com.barbershop.application.validators.EmailValidator;

@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

	private String username;
	private String password;
	private Date birth;
	private Boolean active;

	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany
	private List<Appointment> appointments;

	public User() {
	}

	public User(String username, 
			String cpf, 
			String email, 
			String password, 
			Date birth, 
			Boolean active, 
			Role role) {
		this.username = username;
		this.cpf = CpfFormatter.format(cpf);
		this.email = email;
		this.password = password;
		this.birth = birth;
		this.active = active;
		this.role = role;
		validate();
		encryptPassword(password);
	}

	public void encryptPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getUsername() {
		return username;
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

	public String getPassword() {
		return password;
	}

	@Override
	public void validate() {
		
		if (!EmailValidator.isValid(email)) 
			throw CustomApplicationException.badRequest("Invalid email");
		
		if (!CPFValidator.isValid(cpf)) 
			throw CustomApplicationException.badRequest("Invalid CPF");
		
		if (role.equals(Role.UNDEFINED)) 
			throw CustomApplicationException.badRequest("Select a Valid Role");
		
		if(password.length() < 8)
			throw CustomApplicationException.badRequest("Password must have at least 8 characters");
	}
}
