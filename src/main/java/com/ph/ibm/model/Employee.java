package com.ph.ibm.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee extends BaseAuditBean{

	private String employeeSerial;
	private String managerSerial;
	private String intranetId;
	private String fullName;
	private Long teamId;
	private List<Role> assignedRoles;
	private List<Project> assignedProjects;
	private String password;
	private boolean isActive;
	private Utilization utilization;

	public Employee() {
		super();
	}

	public Employee(String employeeSerial, String managerSerial, String intranetId, String fullName, Long teamId,
			List<Role> assignedRoles, List<Project> assignedProjects, String password, boolean isActive,
			Utilization utilization) {
		super();
		this.employeeSerial = employeeSerial;
		this.managerSerial = managerSerial;
		this.intranetId = intranetId;
		this.fullName = fullName;
		this.teamId = teamId;
		this.assignedRoles = assignedRoles;
		this.assignedProjects = assignedProjects;
		this.password = password;
		this.isActive = isActive;
		this.utilization = utilization;
	}

	public String getEmployeeSerial() {
		return employeeSerial;
	}

	public void setEmployeeSerial(String employeeSerial) {
		this.employeeSerial = employeeSerial;
	}

	public String getManagerSerial() {
		return managerSerial;
	}

	public void setManagerSerial(String managerSerial) {
		this.managerSerial = managerSerial;
	}

	public String getIntranetId() {
		return intranetId;
	}

	public void setIntranetId(String intranetId) {
		this.intranetId = intranetId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public List<Role> getAssignedRoles() {
		return assignedRoles;
	}

	public void setAssignedRoles(List<Role> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	public List<Project> getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(List<Project> assignedProjects) {
		this.assignedProjects = assignedProjects;
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

	public Utilization getUtilization() {
		return utilization;
	}

	public void setUtilization(Utilization utilization) {
		this.utilization = utilization;
	}

}