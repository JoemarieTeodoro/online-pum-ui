package com.ibm.opum.user.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

	private int employeeId;
	private String employeeIdNumber;
	private String email;
	private int projectEngagementId;
	private String firstName;
	private String lastName;
	private String middleName;
	private boolean isAdmin;
	private String fullName;
	private String password;
	private boolean isActive;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public Employee() {

	}

	public Employee(String employeeIdNumber, String email, String password) {
		this.employeeIdNumber = employeeIdNumber;
		this.email = email;
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeIdNumber() {
		return employeeIdNumber;
	}

	public void setEmployeeIdNumber(String employeeIdnumber) {
		this.employeeIdNumber = employeeIdnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProjectEngagementId() {
		return projectEngagementId;
	}

	public void setProjectEngagementId(int projectEngagementId) {
		this.projectEngagementId = projectEngagementId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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