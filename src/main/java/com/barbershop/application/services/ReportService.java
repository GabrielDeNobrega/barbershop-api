package com.barbershop.application.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.barbershop.application.DTOs.AppointmentReportDTO;
import com.barbershop.application.classes.AppointmentReport;
import com.barbershop.application.entities.Appointment;
import com.barbershop.application.enums.AppointmentStatus;
import com.barbershop.application.mappers.AppointmentReportMapper;
import com.barbershop.application.repositories.AppointmentRespository;

@Service
public class ReportService {
	@Autowired
	AppointmentRespository appointmentRespository;

	public AppointmentReportDTO getAppointmentReport(Integer pageNumber, Integer pageSize, Date startDate,
			Date endDate) {
		
		if(startDate == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -30);
			startDate = calendar.getTime();
		};
		
		if(endDate == null) {
			endDate = new Date();
		};
		
		Page<Appointment> appointments = appointmentRespository.findAllOrderByCreatedAtDesc(AppointmentStatus.FINISHED,
				startDate, endDate, Pageable.ofSize(pageSize).withPage(pageNumber));
		
		AppointmentReport appointmentReport = new AppointmentReport(appointments);
		
		return AppointmentReportMapper.reverseMap(appointmentReport);
	}
}
