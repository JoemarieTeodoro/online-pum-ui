package com.ph.ibm.model;

import java.util.List;

public class PUMYearList {
	private List<PUMYear> pumYearList;

	public PUMYearList() {

	}

	public PUMYearList(List<PUMYear> pumYearList) {
		super();
		this.pumYearList = pumYearList;
	}

	public List<PUMYear> getPumYearList() {
		return pumYearList;
	}

	public void setPumYearList(List<PUMYear> pumYearList) {
		this.pumYearList = pumYearList;
	}

}
