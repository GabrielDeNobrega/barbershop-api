package com.barbershop.application.entities;

import java.util.Date;
import org.springframework.http.HttpStatus;
import com.barbershop.application.core.base.classes.BaseEntity;
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
	private Service serviceDetail;
	
	public Appointment() { }
	
	public Appointment(Date start, Date end, Float price, User customer, User employee) {
		this.start = start;
		this.end = end;
		this.price = price;
		this.customer = customer;
		this.employee = employee;
	}
	
	public Appointment(Date start, Date end,User customer, User employee, Service serviceDetail) {
		
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.employee = employee;
		this.serviceDetail = serviceDetail;
		this.price = serviceDetail.getCurrentPrice();
		validate();
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
	
	@Override
	public void validate(){
		if(customer.equals(employee))
			throw new CustomApplicationException(
					"Employee account are no allowed to schedule appointments", 
					HttpStatus.BAD_REQUEST);
			
	}
}

