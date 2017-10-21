package com.ph.ibm.model;

public class UtilizationType {
	private String name;
	private String created;
	private String updated;
	private String createdBy;
	private String updatedBy;

	public UtilizationType() {
	}

	public UtilizationType(String name, String created, String updated, String createdBy, String updatedBy) {
		super();
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
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
