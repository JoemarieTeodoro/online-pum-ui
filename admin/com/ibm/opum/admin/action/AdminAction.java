package com.ibm.opum.admin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibm.opum.admin.bean.JsonToJavaUtil;
import com.ibm.opum.admin.bean.PUMYear;
import com.ibm.opum.admin.bean.PUMYearList;
import com.ibm.opum.calendar.EventsCreator;
import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.user.bean.Holiday;
import com.ibm.opum.user.bean.HolidayList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;

public class AdminAction extends ActionSupport {

	private static final String REST_BASE_URL = ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
			+ "/online-pum-rest/webapi/opum/";
	private Logger logger = Logger.getLogger(AdminAction.class);
	private HolidayList holidayList;
	private static final long serialVersionUID = 1L;
	private PUMYearList pumYearList;
	private String year;
	private List<String> events;
	private Client client;
	private String startDate = "null";
	private String endDate = "null";

	public AdminAction() {
		super();
		ClientConfig clientConfig = ClientConfiguration.getInstance();
		client = Client.create(clientConfig);
	}

	public String home() {
		return "adminHome";
	}

	public String uploadSysAdminCSVLink() {
		String tempEmpLink = REST_BASE_URL + "adminDataLoading";

		assignValuesToSession(tempEmpLink, "Upload Admin List");
		return "uploadCSVLink";
	}

	public String uploadAdminCSVLink() {
		String tempEmpLink = REST_BASE_URL + "dataLoading";

		assignValuesToSession(tempEmpLink, "Upload Employee List");
		return "uploadCSVLink";
	}

    public String uploadTeamListCSVLink() {
        String tempEmpLink = REST_BASE_URL + "teamListdataLoading";

        assignValuesToSession( tempEmpLink, "Upload Team List" );
        return "uploadCSVLink";
    }

	public String uploadEmployeeTeamCSVLink() {
		String tempEmpLink = REST_BASE_URL + "dataLoadingTeamEmpList";

		assignValuesToSession(tempEmpLink, "Upload Employee Team List");
		return "uploadCSVLink";
	}

	public String uploadPEMCSVLink() {
		String tempEmpLink = REST_BASE_URL + "pem";

		assignValuesToSession(tempEmpLink, "Upload PEM List");
		return "uploadCSVLink";
	}

	public String uploadEmployeeRolesCSVLink() {
		String tempEmpLink = REST_BASE_URL + "emprole";

		assignValuesToSession(tempEmpLink, "Upload Employee Roles");
		return "uploadCSVLink";
	}

	public String uploadILCSheet() {
		String tempEmpLink = REST_BASE_URL + "ilcDataLoading";

		assignValuesToSession(tempEmpLink, "Upload ILC Sheet");
		return "uploadILCSheet";
	}
	
	public String downloadUtilizationReport() {
		String tempEmpLink = REST_BASE_URL + "downloadOverallUtilization";
		
		assignValuesToSession(tempEmpLink, "Download Utilization Report");
		return "downloadUtilizationReportLink";
	}

	private void assignValuesToSession(String tempEmpLink, String subtitle) {
		ActionContext.getContext().getSession().put("form_action", tempEmpLink);
		ActionContext.getContext().getSession().put("subtitle", subtitle);
	}

	public String projectEngagement() {
		return "projectEngagementLink";
	}

	public String downloadPUM() {
		String tempEmpLink = REST_BASE_URL + "downloadUtilization";

		assignValuesToSession(tempEmpLink, "Download PUM");

		return "downloadPUMLink";
	}

	public String definePUMYear() {
		String tempEmpLink = REST_BASE_URL + "savePUMYearDate";

		assignValuesToSession(tempEmpLink, "PUM Year Start and End Date");
		return "definePUMYearLink";
	}

	public String definePUMMonth() {
		String tempEmpLink = REST_BASE_URL + "savePUMMonthLink";
		String getPumMonthLink = REST_BASE_URL + "getPUMMonth";

		extractFiscalYears();
		assignValuesToSession(tempEmpLink, "PUM Month End Date");
		ActionContext.getContext().getSession().put("get_PumMonth_Link", getPumMonthLink);
		return "definePUMMonthLink";
	}

	public String viewPUMYear() {
		return "viewPUMYearLink";
	}

	public String viewAllPUMYear() {

		extractFiscalYears();
		return "viewAllPUMYearLink";
	}

	private void extractFiscalYears() {
		String jsonData = null;

		try {
			URL url = new URL(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/online-pum-rest/webapi/opum/yearList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") + "");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") + "");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((jsonData = in.readLine()) != null) {
				logger.info("Jason Data: " + jsonData);
				pumYearList = JsonToJavaUtil.JsonToJava(jsonData, PUMYearList.class);

			}
			for (PUMYear pumyear : pumYearList.getPumYearList()) {
				logger.info("PUMYear:" + pumyear.getPumYear());
				logger.info("Start Date: " + pumyear.getStart());
				logger.info("End Date:" + pumyear.getEnd());
			}

			logger.info("\nCrunchify REST Service Invoked Successfully..");
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String viewSpecificYearDate() {
		try {
			URL url = new URL(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
					+ "/onlinePUM/webapi/opum/yeardate/" + year);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") + "");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") + "");
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String adminCalendar() {
		EventsCreator eventsCreator = new EventsCreator();
		events = eventsCreator.createEvents(client);
		// this can be null when fy is not yet set
		if (eventsCreator.getStartDate() != null && eventsCreator.getEndDate() != null) {
			startDate = eventsCreator.getStartDate();
			endDate = eventsCreator.getEndDate();
		}
		return "adminCalendarLink";
	}

	public String defineHolidays(){
		String tempEmpLink = REST_BASE_URL + "saveHolidays";

		assignValuesToSession(tempEmpLink, "Enter Holidays");
		return "defineHolidaysLink";
	}

	public String searchEmployee() {
        String tempEmpLink = REST_BASE_URL + "searchEmployee/";
        String updateDetailsLink = REST_BASE_URL + "updateEmployeeDetails";

        ActionContext.getContext().getSession().put( "update_action", updateDetailsLink );
        assignValuesToSession( tempEmpLink, "Search Employee" );
        return "searchEmployeeLink";
	}

	public String searchHoliday() {
		String tempEmpLink = REST_BASE_URL + "checkHoliday/";
		String updateHoliday = REST_BASE_URL + "updateHoliday";
		String deleteHoliday = REST_BASE_URL + "deleteHoliday";

		ActionContext.getContext().getSession().put("update_action", updateHoliday);
		ActionContext.getContext().getSession().put("delete_action", deleteHoliday);
		assignValuesToSession(tempEmpLink, "Search Holiday");

		return "searchHolidayLink";
	}

	public String showAllHolidays() throws IOException {
		BufferedReader in = null;
		HttpURLConnection connection = null;
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
			for (Holiday holidays : holidayList.getHolidayList()) {
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
		return "adminShowAllHolidaysLink";
	}

	public String showDatePicker() {
		String editUserPastDateLink = REST_BASE_URL + "insertUserPastDate/";
        assignValuesToSession( editUserPastDateLink, "Edit User Past Date" );
		return "showDatePicker";
	}
	
	public PUMYearList getPumYearList() {
		return pumYearList;
	}

	public void setPumYearList(PUMYearList pumYearList) {
		this.pumYearList = pumYearList;
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

	public HolidayList getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(HolidayList holidayList) {
		this.holidayList = holidayList;
	}
}
