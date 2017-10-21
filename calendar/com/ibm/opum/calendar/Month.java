package com.ibm.opum.calendar;

import java.util.List;

import javax.persistence.Entity;

@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true, dynamicInsert=true
)
public class Month {
	private int month;
	private String monthName;
	private List<Week> weeks;
	
	public Month() {

	}

	public Month(String monthName,List<Week> weeks, int month) {
		this.weeks = weeks;
		this.monthName=monthName;
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
