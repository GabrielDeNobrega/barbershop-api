package com.barbershop.application.DTOs;

public class ServiceDTO {
	public Long id;
	public Float currentPrice;
	public String name;
	public Integer duration;
	public String kindOfCare;

	public ServiceDTO(Long id, Float currentPrice, String name, Integer duration, String kindOfCare) {
		this.id = id;
		this.currentPrice = currentPrice;
		this.name = name;
		this.duration = duration;
		this.kindOfCare = kindOfCare;
	}
}
