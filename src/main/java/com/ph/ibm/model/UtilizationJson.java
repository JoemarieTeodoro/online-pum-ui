package com.ph.ibm.model;

import java.io.Serializable;

public class UtilizationJson implements Serializable {

	private static final long serialVersionUID = 1L;
	private int month;
	private int day;
	private int dayOfMonth;
	private String utilizationHours;
	private String editable;

	public UtilizationJson() {

	}

	public UtilizationJson(int month, int day, int dayOfMonth, String utilizationHours, String editable) {
		super();
		this.month = month;
		this.day = day;
		this.dayOfMonth = dayOfMonth;
		this.utilizationHours = utilizationHours;
		this.editable = editable;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getUtilizationHours() {
		return utilizationHours;
	}

	public void setUtilizationHours(String utilizationHours) {
		this.utilizationHours = utilizationHours;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
