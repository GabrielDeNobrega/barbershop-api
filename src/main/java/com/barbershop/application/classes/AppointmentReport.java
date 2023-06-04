package com.barbershop.application.classes;

import org.springframework.data.domain.Page;
import com.barbershop.application.entities.Appointment;

public class AppointmentReport {
	private Page<Appointment> appointments;
	private Double total;
	
	public AppointmentReport(Page<Appointment> appointments) {
		this.appointments = appointments;
		calculateTotalReport();
	}
	
	private void calculateTotalReport() {
		this.total = appointments.stream().mapToDouble((x)->x.getPrice()).sum();
	}

	public Page<Appointment> getAppointments() {
		return appointments;
	}

	public Double getTotal() {
		return total;
	}
}
