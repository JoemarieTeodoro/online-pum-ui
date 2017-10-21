package com.ph.ibm.model;

import java.util.List;

public class Year {

	private double totalHours;
	private double numberOfVL;
	private double numberOfSL;
	private double numberOfEL;
	private double numberOfOL;
	private double numberOfTR;
	private double numberOfHO;
	private double numberOfCDO;
	private double numberOfAvailableHours;
	private double yearToDateUtilization;
	private List<Quarter> quarters;
	
	public Year(){
		
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

	public double getNumberOfEL() {
		return numberOfEL;
	}

	public void setNumberOfEL(double numberOfEL) {
		this.numberOfEL = numberOfEL;
	}

	public double getNumberOfTR() {
		return numberOfTR;
	}

	public void setNumberOfTR(double numberOfTR) {
		this.numberOfTR = numberOfTR;
	}

	public double getNumberOfHO() {
		return numberOfHO;
	}

	public void setNumberOfHO(double numberOfHO) {
		this.numberOfHO = numberOfHO;
	}

	public double getNumberOfCDO() {
		return numberOfCDO;
	}

	public void setNumberOfCDO(double numberOfCDO) {
		this.numberOfCDO = numberOfCDO;
	}

	public double getNumberOfAvailableHours() {
		return numberOfAvailableHours;
	}

	public void setNumberOfAvailableHours(double numberOfAvailableHours) {
		this.numberOfAvailableHours = numberOfAvailableHours;
	}

	public double getYearToDateUtilization() {
		return yearToDateUtilization;
	}

	public void setYearToDateUtilization(double yearToDateUtilization) {
		this.yearToDateUtilization = yearToDateUtilization;
	}

	public List<Quarter> getQuarters() {
		return quarters;
	}

	public void setQuarters(List<Quarter> quarters) {
		this.quarters = quarters;
	}
	
}
