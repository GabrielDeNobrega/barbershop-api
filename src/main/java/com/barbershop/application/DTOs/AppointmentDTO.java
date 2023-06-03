package com.barbershop.application.DTOs;

import java.util.Date;

public class AppointmentDTO {
	public Long id;
	public Date start;
	public Date end;
	public UserDTO customer;
	public UserDTO employee;
	public ServiceDTO service;
	public Date createdAt;
	public String status;
	
	public AppointmentDTO() {}
	
	public AppointmentDTO(
			Long id, 
			Date start, 
			Date end, 
			UserDTO customer, 
			UserDTO employee, 
			ServiceDTO service,
			Date createdAt,
			String status) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.employee = employee;
		this.service = service;
		this.createdAt = createdAt;
		this.status = status;
	}
}
