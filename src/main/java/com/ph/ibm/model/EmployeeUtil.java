package com.ph.ibm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeUtil {

	private String employeeIdNumber;
	private String projectName;
	private String startDate;
	private String endDate;

	public EmployeeUtil() {

	}

	public EmployeeUtil(String employeeIdNumber, String projectName, String startDate, String endDate) {
		super();
		this.employeeIdNumber = employeeIdNumber;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getEmployeeIdNumber() {
		return employeeIdNumber;
	}

	public void setEmployeeIdNumber(String employeeIdNumber) {
		this.employeeIdNumber = employeeIdNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
