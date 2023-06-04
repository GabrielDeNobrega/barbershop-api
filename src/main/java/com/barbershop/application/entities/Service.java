package com.barbershop.application.entities;

import java.util.List;
import com.barbershop.application.core.base.classes.BaseEntity;
import com.barbershop.application.enums.KindOfCare;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Service extends BaseEntity<Long>{
	private Float currentPrice;
	private String name;
	private Integer duration;
	private Boolean active;
	
	@OneToMany(mappedBy = "service")
	private List<Appointment> appointments;
	
	@Enumerated(EnumType.STRING)
	private KindOfCare kindOfCare;
	
	public Service() { }
	
	public Service(Float currentPrice, String name, Integer duration, KindOfCare kindOfCare) {
		this.currentPrice = currentPrice;
		this.name = name;
		this.duration = duration;
		this.kindOfCare = kindOfCare;
		this.active = true;
		validate();
	}

	public Float getCurrentPrice() {
		return currentPrice;
	}

	public String getName() {
		return name;
	}

	public Integer getDuration() {
		return duration;
	}

	public KindOfCare getKindOfCare() {
		return kindOfCare;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public void validate(){
		if(currentPrice == 0)
			throw CustomApplicationException.badRequest("Price must be greater than 0");
	}
}
