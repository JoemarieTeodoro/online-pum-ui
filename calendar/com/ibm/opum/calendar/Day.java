package com.ibm.opum.calendar;

import javax.persistence.Entity;

@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true, dynamicInsert=true
)
public class Day {
	private String hours;
	private int day;
	private int date;
	private String utilType;
	private String id;
	private boolean isEnabled;
	
	public Day(){
		
	}
	
	public Day(String hours, int day, int date, String utilType, String id, boolean isEnabled) {
		this.hours = hours;
		this.day =day;
		this.date = date;
		this.utilType = utilType;
		this.id = id;
		this.isEnabled = isEnabled;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getUtilType() {
		return utilType;
	}

	public void setUtilType(String utilType) {
		this.utilType = utilType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
