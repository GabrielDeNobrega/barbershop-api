package com.barbershop.application.mappers;

import java.util.List;

import com.barbershop.application.DTOs.AppointmentTimeDTO;
import com.barbershop.application.classes.AppointmentTime;
import com.barbershop.application.entities.Appointment;

public class AppointmentTimeMapper {

	public static AppointmentTime map(Appointment appointment) {
		return new AppointmentTime(appointment.getStart(), appointment.getEnd());
	}

	public static List<AppointmentTime> mapToList(List<Appointment> appointmentList) {
		return appointmentList.stream().map(x -> map(x)).toList();
	}
	
	public static AppointmentTimeDTO map(AppointmentTime appointmentTime) {
		return new AppointmentTimeDTO(appointmentTime.getStart(), appointmentTime.getEnd());
	}

	public static List<AppointmentTimeDTO> map(List<AppointmentTime> appointmentTimeList) {
		return appointmentTimeList.stream().map(x -> map(x)).toList();
	}
}
