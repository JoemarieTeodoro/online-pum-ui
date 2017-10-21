package com.ibm.opum.calendar;

import javax.persistence.Entity;

@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true, dynamicInsert=true
)
public class Week {
	private String weekEndingDate;
	private Day[] days;
	
	
	public Week() {
	}

	public Week(String weekEndingDate, Day[] days, String[] hours) {
		this.weekEndingDate = weekEndingDate;
		this.days = days;
	}

	public String getWeekEndingDate() {
		return weekEndingDate;
	}

	public void setWeekEndingDate(String weekEndingDate) {
		this.weekEndingDate = weekEndingDate;
	}

	public Day[] getDays() {
		return days;
	}

	public void setDays(Day[] days) {
		this.days = days;
	}
}
