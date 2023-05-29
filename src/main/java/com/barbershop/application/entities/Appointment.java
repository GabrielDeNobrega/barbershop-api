package com.barbershop.application.entities;

import java.util.Date;
import com.barbershop.application.core.base.classes.BaseEntity;
import com.barbershop.application.enums.Role;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity<Long> {
	
	private Date start;
	private Date end;
	private Float price;
	private Boolean active;

	@ManyToOne
	private User customer;

	@ManyToOne
	private User employee;
	
	@ManyToOne
	private Service service;
	
	public Appointment() {
		super();
	}
	
	public Appointment(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
		this.active = true;
	}
	
	public Appointment(Date start, Date end, Date createdAt) {
		super(createdAt);
		this.start = start;
		this.end = end;
		this.active = true;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Float getPrice() {
		return price;
	}
	
	public User getCustomer() {
		return customer;
	}
	
	public User getEmployee() {
		return employee;
	}

	public Service getService() {
		return service;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void configureAppointment (User customer, User employee, Service service) {
		this.customer = customer;
		this.employee = employee;
		this.service = service;
		this.price = service.getCurrentPrice();
		validate();
	}
	
	@Override
	public void validate(){
		if(customer.getRole().equals(Role.ADMINISTRATOR) || 
		   customer.getRole().equals(Role.EMPLOYEE))
			throw CustomApplicationException.badRequest(
					"Only Customers are allowed to schedule appointments");
		
		if(!employee.getRole().equals(Role.EMPLOYEE))
			throw CustomApplicationException.badRequest(
					"Unexpected attribution of an appointment to a non-employee user");
	}
}

