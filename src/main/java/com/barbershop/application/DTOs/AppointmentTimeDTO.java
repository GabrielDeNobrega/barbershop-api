package com.barbershop.application.DTOs;

import java.util.Date;

public class AppointmentTimeDTO {
	private Date start;
	private Date end;

	public AppointmentTimeDTO() {
	}
	
	public AppointmentTimeDTO(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public void setEnd(Date end, int endInSeconds) {
		this.end = end;
	}

	public void setStart(Date start, int startInSeconds) {
		this.start = start;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}
}
