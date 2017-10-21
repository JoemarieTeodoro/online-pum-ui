package com.ph.ibm.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account extends BaseAuditBean {
	
	private Long accountId;
	private String accountName;
	private List<Project> projects;
	private Date startDate;
	private Date endDate;

	public Account() {
		super();
	}

	public Account(Long accountId, String accountName, List<Project> projects, Date startDate, Date endDate) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.projects = projects;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
}
