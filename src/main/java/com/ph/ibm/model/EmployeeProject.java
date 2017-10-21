package com.ph.ibm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeProject {
	private String employeeIdNumber;
	private String email;
	private String password;
	private String projectName;

	public EmployeeProject() {
	}

	public EmployeeProject(String employeeIdNumber, String email, String password, String projectName) {
		super();
		this.employeeIdNumber = employeeIdNumber;
		this.email = email;
		this.password = password;
		this.projectName = projectName;
	}

	public String getEmployeeIdNumber() {
		return employeeIdNumber;
	}

	public void setEmployeeIdNumber(String employeeIdNumber) {
		this.employeeIdNumber = employeeIdNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
