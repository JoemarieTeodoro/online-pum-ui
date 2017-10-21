package com.ibm.opum.admin.bean;



public class PUMYear {

	private int yearId;
	private int pumYear;
	private String end;
	private String start;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public PUMYear() {

	}

	public PUMYear(int yearId, int pumYear, String end, String start, String createDate, String createdBy,
			String updateDate, String updatedBy) {
		super();
		this.yearId = yearId;
		this.pumYear = pumYear;
		this.end = end;
		this.start = start;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;

	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getPumYear() {
		return pumYear;
	}

	public void setPumYear(int pumYear) {
		this.pumYear = pumYear;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
