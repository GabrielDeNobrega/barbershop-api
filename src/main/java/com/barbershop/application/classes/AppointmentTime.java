package com.barbershop.application.classes;

import java.util.Calendar;
import java.util.Date;

public class AppointmentTime {
	private Date start;
	private Date end;
	private int startInSeconds;
	private int endInSeconds;

	public AppointmentTime() {
	}

	public AppointmentTime(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
		this.startInSeconds = dateToSeconds(start);
		this.endInSeconds = dateToSeconds(end);
	}

	public void setEnd(Date end, int endInSeconds) {
		this.endInSeconds = endInSeconds;
		this.end = end;
	}

	public void setStart(Date start, int startInSeconds) {
		this.startInSeconds = startInSeconds;
		this.start = start;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public boolean isBetweenStartEndDate(Date start, Date end) {
		return getMedianTimeInSeconds() > dateToSeconds(start) && getMedianTimeInSeconds() < dateToSeconds(end);
	}

	public int getMedianTimeInSeconds() {
		return endInSeconds - ((endInSeconds - startInSeconds) / 2);
	}

	private int dateToSeconds(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int hours = cal.get(Calendar.HOUR_OF_DAY) * 60 * 60;
		int minutes = cal.get(Calendar.MINUTE) * 60;

		return hours + minutes;
	}
}
