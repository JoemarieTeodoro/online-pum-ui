package com.ibm.opum.admin.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.ibm.opum.admin.bean.JsonToJavaUtil;
import com.ibm.opum.admin.bean.PUMYear;
import com.ibm.opum.admin.bean.PUMYearList;
import com.ibm.opum.resourceutils.ClientConfiguration;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;

public class AdminAction extends ActionSupport {

	private static final String REST_BASE_URL = ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
			+ "/online-pum-rest/webapi/opum/";
	private Logger logger = Logger.getLogger(AdminAction.class);
	private static final long serialVersionUID = 1L;
	private PUMYearList pumYearList;
	private String year;

	public AdminAction() {
		super();
		ClientConfig clientConfig = ClientConfiguration.getInstance();
		Client.create(clientConfig);
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
		String tempEmpLink = REST_BASE_URL + "dataLoadingEmpRoles";

		assignValuesToSession(tempEmpLink, "Upload Employee Roles");
		return "uploadCSVLink";
	}

	private void assignValuesToSession(String tempEmpLink, String subtitle) {
		ActionContext.getContext().getSession().put("form_action", tempEmpLink);
		ActionContext.getContext().getSession().put("subtitle", subtitle);
	}

	public String projectEngagement() {
		return "projectEngagementLink";
	}

	public String downloadPUM() {
		return "downloadPUMLink";
	}

	public String definePUMYear() {
		return "definePUMYearLink";
	}

	public String viewPUMYear() {
		return "viewPUMYearLink";
	}

	public String viewAllPUMYear() {

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

		return "viewAllPUMYearLink";
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
	    return "adminCalendarLink";
	}
	
	public String defineHolidays(){
		return "defineHolidaysLink";
	}

	public String searchEmployee() {
		return "searchEmployeeLink";
	}

	public String searchHoliday() {
		return "searchHolidayLink";
	}
	
	public String showAllHolidays() {
	    return "showAllHolidaysLink";
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

}
