package com.ibm.opum.calendar;

import java.util.List;

import javax.persistence.Entity;
@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true, dynamicInsert=true
)
public class Year {
	private List<Month> monthList;
	private int year;
	private String startDate;
	private String endDate;

	public Year(){
		
	}
	
	public Year(List<Month> monthList, int year, String startDate, String endDate) {
		super();
		this.monthList = monthList;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

