package com.barbershop.application.DTOs;

import org.springframework.data.domain.Page;

public class AppointmentReportDTO {
	public Page<AppointmentDTO> appointments;
	public Double total;

	AppointmentReportDTO() {
	}

	public AppointmentReportDTO(Page<AppointmentDTO> appointments, Double total) {
		this.appointments = appointments;
		this.total = total;
	}
}
