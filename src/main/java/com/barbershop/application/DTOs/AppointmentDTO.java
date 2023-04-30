package com.barbershop.application.DTOs;

import java.util.Date;

public class AppointmentDTO {
	public Long id;
	public Date start;
	public Date end;
	public Long custumerId;
	public Long employeeId;
	public Long serviceId;
	public UserDTO customer;
	public UserDTO employee;
	public ServiceDTO service;
	public Date createdAt;
	
	public AppointmentDTO(
			Long id, 
			Date start, 
			Date end, 
			UserDTO customer, 
			UserDTO employee, 
			ServiceDTO service,
			Date createdAt) {
		
		this.id = id;
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.employee = employee;
		this.service = service;
		this.createdAt = createdAt;
	}
}
