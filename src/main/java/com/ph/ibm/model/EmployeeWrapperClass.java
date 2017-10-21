package com.ph.ibm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeWrapperClass {

	private Employee employee;
	private String errorMessage;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
