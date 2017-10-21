package com.ph.ibm.model;

public class PUMMonth {

	private int monthId;
	private int pumMonth;
	private String start;
	private String end;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public PUMMonth(){
		
	}
	
	public PUMMonth(int monthId, int pumMonth, String start, String end, String createDate, String createdBy, String updateDate, String updatedBy){
		super();
		this.monthId = monthId;
		this.pumMonth = pumMonth;
		this.start = start;
		this.end = end;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getPumMonth() {
		return pumMonth;
	}

	public void setPumMonth(int pumMonth) {
		this.pumMonth = pumMonth;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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
