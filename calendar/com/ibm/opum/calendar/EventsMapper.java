package com.ibm.opum.calendar;

public class EventsMapper {
	private int employeeLeaveID;
	private String title;
	private String date;
	private String color;
	private boolean lock;
	private boolean holiday;

	public int getEmployeeLeaveID() {
		return employeeLeaveID;
	}

	public void setEmployeeLeaveID(int employeeLeaveID) {
		this.employeeLeaveID = employeeLeaveID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}
}
