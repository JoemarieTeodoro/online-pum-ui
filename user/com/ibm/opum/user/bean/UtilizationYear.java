package com.ibm.opum.user.bean;

import java.util.List;

public class UtilizationYear {

	private int year;
	private List<Utilization> utilizationJSON;

	public UtilizationYear() {
	}

	public UtilizationYear(int year, List<Utilization> utilizationJSON) {
		this.year = year;
		this.utilizationJSON = utilizationJSON;
	}

	public List<Utilization> getUtilizationJSON() {
		return utilizationJSON;
	}

	public void setUtilization_JSON(List<Utilization> utilizationJSON) {
		this.utilizationJSON = utilizationJSON;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
