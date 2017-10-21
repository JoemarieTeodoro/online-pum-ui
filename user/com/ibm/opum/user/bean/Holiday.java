package com.ibm.opum.user.bean;

public class Holiday {
	private int holiday_Id;
	private String name;
	private String date;
	private String createDate;
	private String updateDate;
	private String createdBy;
	private String updatedBy;

	public Holiday() {
	}

	public Holiday(int holiday_Id, String name, String date, String createDate, String updateDate, String createdBy,
			String updatedBy) {
		super();
		this.holiday_Id = holiday_Id;
		this.name = name;
		this.date = date;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;

	}

	public int getHoliday_Id() {
		return holiday_Id;
	}

	public void setHoliday_Id(int holiday_Id) {
		this.holiday_Id = holiday_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
