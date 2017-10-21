package com.ph.ibm.bo;

import static java.lang.String.format;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ph.ibm.model.Employee;
import com.ph.ibm.model.EmployeeProject;
import com.ph.ibm.model.EmployeeUpdate;
import com.ph.ibm.model.Project;
import com.ph.ibm.model.ProjectEngagement;
import com.ph.ibm.opum.exception.InvalidEmployeeException;
import com.ph.ibm.opum.exception.OpumException;
import com.ph.ibm.repository.EmployeeRepository;
import com.ph.ibm.repository.ProjectEngagementRepository;
import com.ph.ibm.repository.ProjectRepository;
import com.ph.ibm.repository.impl.EmployeeRepositoryImpl;
import com.ph.ibm.repository.impl.ProjectEngagementRepositoryImpl;
import com.ph.ibm.repository.impl.ProjectRepositoryImpl;
import com.ph.ibm.util.FormatValidation;
import com.ph.ibm.util.MD5HashEncrypter;
import com.ph.ibm.util.OpumConstants;

public class EmployeeBO {

	/** EmployeeRepository is a Data Access Object which contain methods to add, register, login, view, validate field/s stored in employee table - opum database */
	private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
	
	/** ProjectRepository is a Data Access Object which contain method to retrieve fields stored in project table - opum database */
	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	
	/** ProjectEngagementRepository is a Data Access Object which contain method to add, save, get, check field/s stored in project_engagement table - opum database */
	private ProjectEngagementRepository projectEngagementRepository = new ProjectEngagementRepositoryImpl();
	
	/** Validation contain methods to validate field such as employee name, employee id, project name, email address */
	private FormatValidation validation = new FormatValidation();
	
	/** Logger is used to document the execution of the system and logs the corresponding log level such as INFO, WARN, ERROR */
	private Logger logger = Logger.getLogger(EmployeeBO.class);
	
	/**
	 * This method is used to register user
	 * 
	 * @param companyIDNumber
	 * @param projectName
	 * @param email
	 * @param password
	 * @return String
	 * @throws Exception 
	 */
	public String registerEmployee(String employeeIdNumber, String projectName, String email, String password) throws Exception {
		// validate company id using regular expression pattern matching
		if (!(validation.isValidEmployeeId(employeeIdNumber))) {
			logger.info("CAUSE OF ERROR: " + OpumConstants.INVALID_COMPANY_ID);
			throw new InvalidEmployeeException(OpumConstants.INVALID_COMPANY_ID);
		}
		
		// validate email address using regular expression pattern matching
		if (!(validation.isValidEmailAddress(email))) {
			logger.error("CAUSE OF ERROR: " + OpumConstants.INVALID_EMAIL_ID);
			throw new InvalidEmployeeException(OpumConstants.INVALID_EMAIL_ID);
		}
		
		// validate project name using regular expression pattern matching
		if (!(validation.isValidProjectName(projectName))) {
			logger.error("CAUSE OF ERROR: " + OpumConstants.INVALID_PROJECT_NAME);
			throw new InvalidEmployeeException(OpumConstants.INVALID_PROJECT_NAME);
		}
		
		// validate if employee id number and email exist in database
		if (!employeeRepository.doesEmployeeExist(employeeIdNumber, email)) {
			logger.info("CAUSE OF ERROR: " + OpumConstants.EMPLOYEE_ID_EMAIL_NOT_FOUND);
			throw new InvalidEmployeeException(OpumConstants.EMPLOYEE_ID_EMAIL_NOT_FOUND);
		}
		
		String hashed = MD5HashEncrypter.computeMD5Digest(password);
		EmployeeProject employeeProject = new EmployeeProject(employeeIdNumber, email, hashed, projectName);
		List<Project> projects = new ArrayList<Project>();
		projects = projectRepository.retrieveData();
		ProjectEngagement projectEngagement = new ProjectEngagement();
		
		// check if USAA Project exists
		for (Project project : projects) {
			if (!project.getName().equals(employeeProject.getProjectName())) {
				continue;
			}
			projectEngagement.setProjectId(project.getProjectId());
		}
		Employee employee = new Employee();
		employee.setEmployeeIdNumber(employeeProject.getEmployeeIdNumber());
		employee.setEmail(employeeProject.getEmail());
		employee.setPassword(employeeProject.getPassword());
		//Employee employee = new Employee(employeeProject.getEmployeeIdNumber(), employeeProject.getEmail(), employeeProject.getPassword());
		if (!employeeRepository.registerEmployee(employee)) {
			logger.info(format("Unsuccessful Registration for employee %s %s", employeeProject.getEmployeeIdNumber(), employeeProject.getEmail()));
			throw new InvalidEmployeeException(OpumConstants.INVALID_COMPANY_ID);
		}
		projectEngagement.setEmployeeId(Integer.parseInt(employeeRepository.viewEmployee(employeeProject.getEmployeeIdNumber())));
		projectEngagementRepository.addProjectEngagement(projectEngagement);

		logger.info(OpumConstants.SUCCESSFULLY_REGISTERED);
		return "Employee " + employeeProject.getEmployeeIdNumber() + " - " + employeeProject.getEmail()
				+ " was successfully registered";
	}
	
	/**
	 * This method is used to login user
	 * 
	 * @param username
	 * @param password
	 * @return Employee object
	 * @throws Exception
	 */
	public Employee loginEmployee(String username, String password) throws Exception {
		String hashed = MD5HashEncrypter.computeMD5Digest(password);
		Employee employee = null;
		try {
			employee = employeeRepository.loginAdmin(username, hashed);
			if (employee != null && employee.isAdmin() == true) {
				try {
					/*logger.info(OpumConstants.ENTERING_ADMIN_PAGE);
					System.out.println(OpumConstants.ENTERING_ADMIN_PAGE);
					URI uri = new URI("http://localhost:8080/onlinePUM-UI/admin/adminHomeLink");
					java.awt.Desktop.getDesktop().browse(uri);
					System.out.println(OpumConstants.OPENING_BROWSER);*/
				} catch (Exception e) {
					logger.error(e);
					throw new OpumException(e.getMessage(), e);
				}
			} else {
				employee = employeeRepository.loginUser(username, hashed);
				if (employee != null) {
					try {
						/*logger.info(OpumConstants.ENTERING_USER_PAGE);
						System.out.println(OpumConstants.ENTERING_USER_PAGE);
						URI uri = new URI("http://localhost:8080/onlinePUM-UI/user/userLink");
						java.awt.Desktop.getDesktop().browse(uri);
						System.out.println(OpumConstants.OPENING_BROWSER);*/
						
					} catch (Exception e) {
						logger.error(e);
						throw new OpumException(e.getMessage(), e);
					}
				} else {
					try {
						/*logger.info(OpumConstants.RETURNING_TO_LOGIN_PAGE);
						System.out.println(OpumConstants.RETURNING_TO_LOGIN_PAGE);
						URI uri = new URI("http://localhost:8080/onlinePUM-UI/login/loginLink");
						java.awt.Desktop.getDesktop().browse(uri);
						System.out.println(OpumConstants.OPENING_BROWSER);*/
					} catch (Exception e) {
						logger.error(e);
						throw new OpumException(e.getMessage(), e);
					}
				}
			}
		}  catch (Exception e) {
			logger.error(e);
			throw new OpumException(e.getMessage(), e);
		}
		return employee;
	}
	
	/**
	 * This method is used to search employee
	 * 
	 * @param employeeIdNumber
	 * @return EmployeeUpdate object
	 * @throws SQLException
	 */
	public EmployeeUpdate searchEmployee(String employeeIdNumber) throws SQLException {
		return employeeRepository.searchEmployee(employeeIdNumber);
	}
	
	/**
	 * This method is used to update employee details
	 * 
	 * @param employeeUpdate
	 * @return boolean
	 * @throws BatchUpdateException
	 * @throws SQLException
	 */
	public boolean updateEmployee(EmployeeUpdate employeeUpdate) throws BatchUpdateException, SQLException {
		return employeeRepository.updateEmployee(employeeUpdate);
	}
	
}
