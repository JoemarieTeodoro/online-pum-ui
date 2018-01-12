package com.ibm.opum.user.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ibm.opum.calendar.EventsCreator;
import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.user.bean.EmployeeEvent;
import com.ibm.opum.user.bean.EmployeeLeave;
import com.ibm.opum.user.bean.ForApproval;
import com.ibm.opum.user.bean.ForApprovalList;
import com.ibm.opum.user.bean.Holiday;
import com.ibm.opum.user.bean.HolidayList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;


public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(UserAction.class);
	private static final String REST_BASE_URL = ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
			+ "/online-pum-rest/webapi/opum/";
	private HolidayList holidayList;
	private ForApprovalList forApprovalList;
	private YearCalculation yearCalculation;
	private String year;
	private Client client;
	private List<String> events;
	/** Default is "''" so that js will treat it as empty string */
	private String startFYDate = "''";
	private String endFYDate = "''";
	private String yearID = "''";
	private String employeeID = "''";
	private String leaveEntry;
	private boolean locked, recoverable;
	private boolean isDraft;

	public UserAction() {
		super();
		ClientConfig clientConfig = ClientConfiguration.getInstance();
		client = Client.create(clientConfig);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String home() {
		return "userLink";
	}

	public String showCalendar() {
		EventsCreator eventsCreator = new EventsCreator();
		events = eventsCreator.createEvents(client);
		if (eventsCreator.getStartDate() != null && eventsCreator.getEndDate() != null) {
			startFYDate = eventsCreator.getStartDate();
			endFYDate = eventsCreator.getEndDate();
			employeeID = eventsCreator.getEmployeeID();
			yearID = eventsCreator.getYearID();
			locked = eventsCreator.isLocked();
		}
		return "calendarLink";
	}

	public String requestLeave(){
		ObjectMapper objectMapper = new ObjectMapper();
		
	    try {
	    	EventsCreator eventsCreator = new EventsCreator();
	    	events = eventsCreator.createEvents(client);
	    	employeeID = eventsCreator.getEmployeeID();
			yearID = eventsCreator.getYearID();
			
			List<EmployeeLeave> leaveRequests = objectMapper.readValue(
					"[" + leaveEntry + "]",
			        objectMapper.getTypeFactory().constructCollectionType(
			                List.class, EmployeeLeave.class));
			EmployeeEvent leaveRequestContainer = new EmployeeEvent();
			leaveRequestContainer.setEmpID(employeeID);
			leaveRequestContainer.setFyID(yearID);
			leaveRequestContainer.setDraft(isDraft());
			leaveRequestContainer.setEmpLeaveList(leaveRequests);
			WebResource webResource = client.resource(REST_BASE_URL + "employeeLeave/");
			String empEvent = webResource.type(MediaType.APPLICATION_JSON).post(String.class, leaveRequestContainer);
			
			if (empEvent != null && empEvent.equals("true")) {
				showCalendar();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "leaveDraftLink";
	}

	public String inputYear(){
        String tempEmpLink = REST_BASE_URL + "myUtilization/";
        assignValuesToSession( tempEmpLink, "My Utilization Summary" );
		return "inputYearLink";
	}

	public String utilizationSummary(){
		String jsonData = null;

		int employeeID = (int) ActionContext.getContext().getSession().get("employeeID");

		try{
			URL url = new URL(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/onlinePUM/webapi/opum/getComputation/" + employeeID + "/" + year);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") +"");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") +"");
			if(connection.getResponseCode() != 200){
				throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			jsonData = in.readLine();
			if(jsonData != null){
				System.out.println("JSON DATA: " + jsonData);
				yearCalculation = JsonToJavaUtil.JsonToJava(jsonData, YearCalculation.class);
			} else{
				yearCalculation = null;
			}
			System.out.println("\nSuccessfully invoked getComputation Web Service!");
			in.close();
			connection.disconnect();
				} catch(Exception e){
					e.printStackTrace();
				}

		//YTD computation
		try{
			System.out.println("Employee ID: " + employeeID);
			URL url = new URL(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/onlinePUM/webapi/opum/getYTDComputation/" + employeeID + "/" + year);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") +"");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") +"");
			if(connection.getResponseCode() != 200){
				throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			jsonData = in.readLine();
			if(jsonData != null){
				System.out.println("JSON DATA: " + jsonData);
				YearCalculation yearUtil = new YearCalculation();
				yearUtil = JsonToJavaUtil.JsonToJava(jsonData, YearCalculation.class);
				yearCalculation.setNumberOfAvailableHours(yearUtil.getNumberOfAvailableHours());
				yearCalculation.setNumberOfCDO(yearUtil.getNumberOfCDO());
				yearCalculation.setNumberOfEL(yearUtil.getNumberOfEL());
				yearCalculation.setNumberOfHO(yearUtil.getNumberOfHO());
				yearCalculation.setNumberOfOL(yearUtil.getNumberOfOL());
				yearCalculation.setNumberOfSL(yearUtil.getNumberOfSL());
				yearCalculation.setNumberOfTR(yearUtil.getNumberOfTR());
				yearCalculation.setNumberOfVL(yearUtil.getNumberOfVL());
				yearCalculation.setTotalHours(yearUtil.getTotalHours());
				yearCalculation.setYearToDateUtilization(yearUtil.getYearToDateUtilization());
			} else{
				yearCalculation = null;
			}
			System.out.println("\nSuccessfully invoked getYTDComputation Web Service!");
			in.close();
			connection.disconnect();
				} catch(Exception e){
					e.printStackTrace();
				}
		return "utilizationSummaryLink";
	}

	public String showForApproval() {

		String jsonData = null;
		try {
			URL url = new URL(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/online-pum-rest/webapi/opum/forApprovalList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String tempEmpLink = ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/online-pum-rest/webapi/leaveToBeApproved";

			assignValuesToSession(tempEmpLink, "Approve or Reject Leaves");

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") +"");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") +"");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((jsonData = in.readLine()) != null) {
				System.out.println("Jason Data: " + jsonData);
				forApprovalList = JsonToJavaUtil.JsonToJava(jsonData, ForApprovalList.class);
				//holidayList.add(holiday);
			}
			for(ForApproval forApprovals:forApprovalList.getForApprovalList()){
				System.out.print("Employee Leave Id: "+forApprovals.getEmployee_Leave_Id());
				System.out.println(" , Employee Id: "+ forApprovals.getEmployee_Id());
			}

			System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showForApprovalLink";
	}

	private void assignValuesToSession(String tempEmpLink, String subtitle) {
		ActionContext.getContext().getSession().put("form_action", tempEmpLink);
		ActionContext.getContext().getSession().put("subtitle", subtitle);
	}

	public String showAllHolidays() throws IOException {
		HttpURLConnection connection = null;
		BufferedReader in = null;
		String jsonData = null;
		try {
			URL url = new URL(REST_BASE_URL + "holidayList");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username").toString());
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password").toString());
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((jsonData = in.readLine()) != null) {
				holidayList = JsonToJavaUtil.JsonToJava(jsonData, HolidayList.class);
			}
			for(Holiday holidays:holidayList.getHolidayList()){
				logger.info("Holiday Name: " + holidays.getName());
				logger.info(" , Holiday Date: " + holidays.getDate());
			}
			logger.info("\nCrunchify REST Service Invoked Successfully..");

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			in.close();
			connection.disconnect();
		}

		return "showAllHolidaysLink";
	}

	//GETTERS&SETTERS
	public HolidayList getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(HolidayList holidayList) {
		this.holidayList = holidayList;
	}

  public ForApprovalList getForApprovalList() {
		return forApprovalList;
	}

	public void setForApprovalList(ForApprovalList forApprovalList) {
		this.forApprovalList = forApprovalList;
	}

	public YearCalculation getYearCalculation() {
		return yearCalculation;
	}

	public void setYearCalculation(YearCalculation yearCalculation) {
		this.yearCalculation = yearCalculation;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

	public String getStartFYDate() {
		return startFYDate;
	}

	public void setStartFYDate(String startFYDate) {
		this.startFYDate = startFYDate;
	}

	public String getEndFYDate() {
		return endFYDate;
	}

	public void setEndFYDate(String endFYDate) {
		this.endFYDate = endFYDate;
	}

	public String getLeaveEntry() {
		return leaveEntry;
	}

	public void setLeaveEntry(String leaveEntry) {
		this.leaveEntry = leaveEntry;
	}

	public String getYearID() {
		return yearID;
	}

	public void setYearID(String yearID) {
		this.yearID = yearID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
  
	public boolean isRecoverable() {
		return recoverable;
	}

	public void setRecoverable(boolean recoverable) {
		this.recoverable = recoverable;
	}

	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}
}