package com.ph.ibm.model;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectEngagement {

	private int projectEngagementId;
	private int projectId;
	private int employeeId;
	private Date startDate;
	private Date endDate;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public ProjectEngagement() {

	}

	public ProjectEngagement(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public ProjectEngagement(int projectEngagementId, int projectId, int employeeId, Date startDate, Date endDate,
			String createDate, String updateDate, String createdBy, String updatedBy) {
		super();
		this.projectEngagementId = projectEngagementId;
		this.projectId = projectId;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public int getProjectEngagementId() {
		return projectEngagementId;
	}

	public void setProjectEngagementId(int projectEngagementId) {
		this.projectEngagementId = projectEngagementId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreated(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdated(String updateDate) {
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
