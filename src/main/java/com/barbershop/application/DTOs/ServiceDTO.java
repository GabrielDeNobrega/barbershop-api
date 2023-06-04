package com.barbershop.application.DTOs;

import java.util.Date;

public class ServiceDTO {
	public Long id;
	public Float currentPrice;
	public String name;
	public Integer duration;
	public String kindOfCare;
	public Boolean active;
	public Date createdAt;

	public ServiceDTO() {
	}

	public ServiceDTO(
			Long id, 
			Float currentPrice, 
			String name, 
			Integer duration, 
			String kindOfCare, 
			Boolean active,
			Date createdAt) {
		super();
		this.id = id;
		this.currentPrice = currentPrice;
		this.name = name;
		this.duration = duration;
		this.kindOfCare = kindOfCare;
		this.active = active;
		this.createdAt = createdAt;
	}
}
