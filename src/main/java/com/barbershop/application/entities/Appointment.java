package com.barbershop.application.entities;

import java.util.Date;
import org.springframework.http.HttpStatus;
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

	@ManyToOne
	private User customer;

	@ManyToOne
	private User employee;
	
	@ManyToOne
	private Service service;
	
	public Appointment() { }
	
	public Appointment(Date start, Date end) {
		this.start = start;
		this.end = end;
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

	public void ConfigureAppointment (User custumer, User employee, Service service) {
		this.customer = custumer;
		this.employee = employee;
		this.service = service;
		this.price = service.getCurrentPrice();
		validate();
	}
	
	@Override
	public void validate(){
		if(customer.getRole().equals(Role.ADMINISTRATOR) || 
		   customer.getRole().equals(Role.EMPLOYEE))
			throw new CustomApplicationException(
					"Only Custumers are allowed to schedule appointments", 
					HttpStatus.BAD_REQUEST);
		
		if(!employee.getRole().equals(Role.EMPLOYEE))
			throw new CustomApplicationException(
					"Unexpected attribution of an appointment to a non-employee user", 
					HttpStatus.BAD_REQUEST);
	}
}

