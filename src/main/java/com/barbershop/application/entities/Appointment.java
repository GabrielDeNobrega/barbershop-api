package com.barbershop.application.entities;

import java.util.Date;

import com.barbershop.application.core.base.classes.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity<Long> {

	private Date start;
	private Date end;

	@ManyToOne
	private User customer;

	@ManyToOne
	private User employee;

	public Appointment() {
	}

	public Appointment(Long id, Date start, Date end, User customer, User employee) {
		super(id);
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.employee = employee;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}
}
