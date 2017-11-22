package com.ibm.opum.user.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.user.bean.Holiday;
import com.ibm.opum.user.bean.HolidayList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class UserAction extends ActionSupport {


	private static final long serialVersionUID = 1L;
	private HolidayList holidayList;
	private YearCalculation yearCalculation;
	private String year;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String home() {
		return "userLink";
	}

	public String utilization() {
		return "calendarLink";
	}

	public String viewMyHours() {
		return "viewMyHoursLink";
	}
	
	public String inputYear(){
		return "inputYearLink";
	}

	public String utilizationSummary(){
		String jsonData = null;
		
		int employeeID = (int) ActionContext.getContext().getSession().get("employeeID");
		
		try{
			System.out.println("Employee ID: " + employeeID);
			URL url = new URL(ClientConfiguration.serverURL + "/onlinePUM/webapi/opum/getComputation/" + employeeID + "/" + year);
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
			URL url = new URL(ClientConfiguration.serverURL + "/onlinePUM/webapi/opum/getYTDComputation/"+employeeID+"/"+year);
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

	public String showAllHolidays() {
		
		String jsonData = null;
		try {
			URL url = new URL(ClientConfiguration.serverURL + "/onlinePUM/webapi/opum/holidayList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") +"");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") +"");
			System.out.println("Hello");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((jsonData = in.readLine()) != null) {
				System.out.println("Jason Data: " + jsonData);
				holidayList = JsonToJavaUtil.JsonToJava(jsonData, HolidayList.class);
				//holidayList.add(holiday);
			}
			for(Holiday holidays:holidayList.getHolidayList()){
				
					System.out.print("Holiday Name: "+holidays.getName());
					System.out.println(" , Holiday Date: "+holidays.getDate());
					
				
			}

			System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
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


}