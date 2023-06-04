package com.barbershop.application.mappers;

import com.barbershop.application.DTOs.AppointmentReportDTO;
import com.barbershop.application.classes.AppointmentReport;
public class AppointmentReportMapper {
	public static AppointmentReportDTO reverseMap(AppointmentReport appointmentReport) {
		return new AppointmentReportDTO(
				AppointmentMapper.reverseMap(appointmentReport.getAppointments()),
				appointmentReport.getTotal());
	}
}
