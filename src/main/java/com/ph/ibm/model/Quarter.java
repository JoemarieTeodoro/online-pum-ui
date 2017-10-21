package com.ph.ibm.model;

import java.util.List;

public class Quarter {

	private double totalHours;
	private double numberOfAvailableHours;
	private double quarterToDateUtilization;
	private List<Month> months;
	private String name;
	
	
	public Quarter(){
		
	}
	
	public double getTotalHours() {
		return totalHours;
	}


	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}


	public double getNumberOfAvailableHours() {
		return numberOfAvailableHours;
	}


	public void setNumberOfAvailableHours(double numberOfAvailableHours) {
		this.numberOfAvailableHours = numberOfAvailableHours;
	}


	public double getQuarterToDateUtilization() {
		return quarterToDateUtilization;
	}


	public void setQuarterToDateUtilization(double quarterToDateUtilization) {
		this.quarterToDateUtilization = quarterToDateUtilization;
	}


	public List<Month> getMonths() {
		return months;
	}


	public void setMonths(List<Month> months) {
		this.months = months;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
