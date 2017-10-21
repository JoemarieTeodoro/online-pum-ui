package com.ibm.opum.user.bean;

import java.util.List;

public class HolidayList {

	private List<Holiday> holidayList;
	

	public HolidayList(List<Holiday> holidayList) {
		super();
		this.holidayList = holidayList;
	}

	public HolidayList() {
		
	}

	public List<Holiday> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<Holiday> holidayList) {
		this.holidayList = holidayList;
	}
	
}
