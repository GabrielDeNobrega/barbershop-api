package com.barbershop.application.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.application.DTOs.AppointmentTimeDTO;
import com.barbershop.application.classes.AppointmentTime;
import com.barbershop.application.mappers.AppointmentTimeMapper;
import com.barbershop.application.repositories.AppointmentRespository;

@Service
public class AppointmentTimeService {

	private final int timeStart = 8 * 60;
	private final int timeEnd = 19 * 60;
	private final int minutesInterval = 30;
	private final int lunchTimeHour = 12;
	
	@Autowired
	private AppointmentRespository appointmentRespository;

	public List<AppointmentTimeDTO> getAvailableAppointmentTimes(Long employeeId, Date day) {

		List<AppointmentTime> employeeAppointmentTimes = AppointmentTimeMapper
				.mapToList(appointmentRespository.findByEmployeeIdAndCreatedAt(employeeId, day));

		List<AppointmentTime> appointmentTimes = getAppointmentTimes(day, timeStart, timeEnd, minutesInterval);
		
		List<AppointmentTime> appointmentsToRemove = new ArrayList<>();
		
		for (AppointmentTime employeeTimes : employeeAppointmentTimes) {
			for (AppointmentTime appointment : appointmentTimes) {
				if (employeeTimes.isBetweenStartEndDate(appointment.getStart(), appointment.getEnd())) {
					appointmentsToRemove.add(appointment);
				}
			}
		}
		
		appointmentTimes.removeAll(appointmentsToRemove);
		
		return AppointmentTimeMapper.map(appointmentTimes);
	}

	
	
	public List<AppointmentTime> getAppointmentTimes(Date date, int start, int end, int interval) {
		Calendar calendar = Calendar.getInstance();
		List<AppointmentTime> AppointmentTimes = new ArrayList<>();
		for (int time = start; time < end; time += interval) {
			if (time/60 == lunchTimeHour) continue;
			
			AppointmentTime times = new AppointmentTime();
			for (int min = 0; min < 60; min += interval) {
				calendar.setTime(date);
				removeHourMinuteSeconds(calendar);

				int interv = (min == 0 ? 0 : interval);
				int hours = hoursToSeconds((time + interv) / 60);
				int minutes = minutesToSeconds((time + interv) % 60);
				int sumOfTime = hours + minutes;

				calendar.add(Calendar.SECOND, sumOfTime);

				if (times.getStart() == null) {
					times.setStart(calendar.getTime(), sumOfTime);
					continue;
				}
				times.setEnd(calendar.getTime(), sumOfTime);
			}
			AppointmentTimes.add(times);
		}
		return AppointmentTimes;
	}

	public static void removeHourMinuteSeconds(Calendar cal) {
		Arrays.asList(Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND)
				.forEach((calendarOption) -> cal.set(calendarOption, 0));
	}

	private static int hoursToSeconds(int hour) {
		return hour * 60 * 60;
	}

	private static int minutesToSeconds(int minutes) {
		return minutes * 60;
	}
}
