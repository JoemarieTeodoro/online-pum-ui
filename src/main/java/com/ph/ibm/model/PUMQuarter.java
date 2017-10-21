package com.ph.ibm.model;

public class PUMQuarter {

	private int quarterId;
	private int pumQuarter;
	private String start;
	private String end;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public PUMQuarter(){
		
	}
	
	public PUMQuarter(int quarterId, int pumQuarter, String start, String end, String createDate, String createdBy, String updateDate, String updatedBy){
		super();
		this.quarterId = quarterId;
		this.pumQuarter = pumQuarter;
		this.start = start;
		this.end = end;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
	}

	public int getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(int quarterId) {
		this.quarterId = quarterId;
	}

	public int getPumQuarter() {
		return pumQuarter;
	}

	public void setPumQuarter(int pumQuarter) {
		this.pumQuarter = pumQuarter;
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
