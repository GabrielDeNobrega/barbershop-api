package com.barbershop.application.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date start;
	private Date end;
	@OneToMany(mappedBy = "appointments")
	private User customer;
	@OneToMany(mappedBy = "appointments")
	private User employee;

	public Appointment() {
	}

	public Appointment(Long id, Date start, Date end, User customer, User employee) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.employee = employee;
	}

}
