package com.ph.ibm.model;

import java.util.List;

public class Month {

	private double totalHours;
	private double numberOfVL;
	private double numberOfSL;
	private double numberOfOL;
	private double numberOfAvailableHours;
	private double monthToDateUtilization;
	private List<Week> weeks;
	private String name;
	
	public Month(){
		
	}
	
	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public double getNumberOfVL() {
		return numberOfVL;
	}

	public void setNumberOfVL(double numberOfVL) {
		this.numberOfVL = numberOfVL;
	}

	public double getNumberOfSL() {
		return numberOfSL;
	}

	public void setNumberOfSL(double numberOfSL) {
		this.numberOfSL = numberOfSL;
	}

	public double getNumberOfOL() {
		return numberOfOL;
	}

	public void setNumberOfOL(double numberOfOL) {
		this.numberOfOL = numberOfOL;
	}

	public double getNumberOfAvailableHours() {
		return numberOfAvailableHours;
	}

	public void setNumberOfAvailableHours(double numberOfAvailableHours) {
		this.numberOfAvailableHours = numberOfAvailableHours;
	}

	public double getMonthToDateUtilization() {
		return monthToDateUtilization;
	}

	public void setMonthToDateUtilization(double monthToDateUtilization) {
		this.monthToDateUtilization = monthToDateUtilization;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
