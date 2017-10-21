package com.ph.ibm.model;

import java.util.List;

public class HolidayList {

	private List<Holiday> holidayList;
	

	public HolidayList(List<Holiday> holidayList) {
		super();
		this.holidayList = holidayList;
	}

	public HolidayList() {
		// TODO Auto-generated constructor stub
	}

	public List<Holiday> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<Holiday> holidayList) {
		this.holidayList = holidayList;
	}
	
}
