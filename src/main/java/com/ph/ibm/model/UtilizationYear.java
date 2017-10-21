package com.ph.ibm.model;

import java.util.List;

public class UtilizationYear {

	private int year;
	private List<UtilizationJson> utilizationJSON;

	public UtilizationYear() {
	}

	public UtilizationYear(int year, List<UtilizationJson> utilizationJSON) {
		this.year = year;
		this.utilizationJSON = utilizationJSON;
	}

	public List<UtilizationJson> getUtilizationJSON() {
		return utilizationJSON;
	}

	public void setUtilizationJSON(List<UtilizationJson> utilizationJSON) {
		this.utilizationJSON = utilizationJSON;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
