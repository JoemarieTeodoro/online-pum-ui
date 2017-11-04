package com.ibm.opum.calendar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.opum.user.action.JavaToJsonUtil;
import com.ibm.opum.user.action.JsonToJavaUtil;
import com.ibm.opum.user.bean.EmployeeUpdate;
import com.ibm.opum.user.bean.Utilization;
import com.ibm.opum.user.bean.UtilizationYear;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateCalendarAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static Year year;
	private String utilJSON;
	private UtilizationYear utilizationYear;

	public String getCalendar() throws ParseException {
		Calendar cal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		year.setYear(Integer.parseInt(year.getEndDate().substring(0, 4)));
		cal.set(Integer.parseInt(year.getStartDate().substring(0, 4)),
				Integer.parseInt(year.getStartDate().substring(5, 7)) - 1,
				Integer.parseInt(year.getStartDate().substring(8, 10)), 0, 0);
		endCal.set(Integer.parseInt(year.getEndDate().substring(0, 4)),
				Integer.parseInt(year.getEndDate().substring(5, 7)) - 1,
				Integer.parseInt(year.getEndDate().substring(8, 10)), 0, 0);
		year.setMonthList(new ArrayList<Month>());
		int monthCounter = Integer.parseInt(year.getStartDate().substring(5, 7)) - 2;
		int monthListCounter = -1;
		int weekendCounter = 0;
		utilizationYear = fetchUtilization(Integer.parseInt(year.getEndDate().substring(0, 4)));
		do {
			if (cal.get(Calendar.MONTH) != monthCounter) {
				monthListCounter++;
				year.getMonthList().add(new Month(getMonthName(cal.get(Calendar.MONTH)), new ArrayList<Week>(),
						cal.get(Calendar.MONTH)));
				monthCounter = cal.get(Calendar.MONTH);
				year.getMonthList().get(monthListCounter).getWeeks().add(new Week());
				weekendCounter = 0;
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter).setDays(new Day[7]);
				for (int i = 0; i < 7; i += 1) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter).getDays()[i] = new Day();
				}
			}
			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				Day day = null;
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
						.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setDate(cal.get(Calendar.DATE));
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
						.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setDay(cal.get(Calendar.DAY_OF_WEEK));
				day = getUtilDayValue(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
				if (utilizationYear == null) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(true);
				}
				if (day != null) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setHours(day.getHours());
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(day.getIsEnabled());
				} else {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(true);
				}
				year.getMonthList().get(monthListCounter).getWeeks().add(new Week());
				weekendCounter++;
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter).setDays(new Day[7]);
				for (int i = 0; i < 7; i += 1) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter).getDays()[i] = new Day();
				}
			} else {
				Day day = null;
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
						.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setDate(cal.get(Calendar.DATE));
				year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
						.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setDay(cal.get(Calendar.DAY_OF_WEEK));
				day = getUtilDayValue(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
				if (utilizationYear == null) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(true);
				}
				if (day != null) {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setHours(day.getHours());
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(day.getIsEnabled());
				} else {
					year.getMonthList().get(monthListCounter).getWeeks().get(weekendCounter)
							.getDays()[cal.get(Calendar.DAY_OF_WEEK) - 1].setIsEnabled(true);
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.before(endCal) || cal.equals(endCal));
		return "success";
	}

	public Day getUtilDayValue(int month, int dayOfMonth) {
		Day day = null;
		if (utilizationYear != null) {
			for (Utilization util : utilizationYear.getUtilizationJSON()) {
				if (util.getMonth() == month && util.getDayOfMonth() == dayOfMonth) {
					day = new Day();
					day.setDay(util.getDay());
					day.setDate(util.getDayOfMonth());
					day.setHours(util.getUtilizationHours());
					if (util.getEditable().equals("D") && (day.getHours() != null && !day.getHours().equals(""))) {
						day.setIsEnabled(false);
					} else {
						day.setIsEnabled(true);
					}
				}
			}
		}
		return day;
	}

	public UtilizationYear fetchUtilization(int year) {
		String jsonData = null;
		UtilizationYear utilYear = new UtilizationYear();
		int employeeID = (int) ActionContext.getContext().getSession().get("employeeID");
		try {
			URL url = new URL("http://localhost:9090/onlinePUM/webapi/opum/utilization/" + employeeID + "/" + year);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") + "");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") + "");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			jsonData = in.readLine();
			if (jsonData != null) {
				utilYear = JsonToJavaUtil.JsonToJava(jsonData, UtilizationYear.class);
			} else {
				utilYear = null;
			}
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utilYear;
	}

	public String saveUtilization() throws JSONException {
		UtilizationYear utilYear = new UtilizationYear();
		utilYear.setYear(year.getYear());
		utilYear.setUtilization_JSON(new ArrayList<Utilization>());
		for (Month month : year.getMonthList()) {
			for (Week week : month.getWeeks()) {
				for (Day day : week.getDays()) {
					if (day.getDate() != 0) {
						utilYear.getUtilizationJSON().add(
								new Utilization(month.getMonth() + 1, day.getDay(), day.getDate(), day.getHours(), ""));
					}
				}
			}
		}

		utilJSON = JavaToJsonUtil.javaToJson(utilYear);

		JSONObject jsonObject = new JSONObject(utilJSON);
		try {
			int employeeID = (int) ActionContext.getContext().getSession().get("employeeID");
			String email = (String) ActionContext.getContext().getSession().get("email");

			URL url = new URL("http://localhost:9090/onlinePUM/webapi/opum/utilization/" + employeeID + "/2017");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") + "");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") + "");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonObject.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (in.readLine() != null) {
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return "success";
	}

	public EmployeeUpdate getEmployeeDetails(String employeeIdNumber) {
		String jsonData = null;
		EmployeeUpdate employee = new EmployeeUpdate();
		try {
			URL url = new URL("http://localhost:9090/onlinePUM/webapi/opum/searchEmployee/" + employeeIdNumber);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("username", ActionContext.getContext().getSession().get("username") + "");
			connection.setRequestProperty("password", ActionContext.getContext().getSession().get("password") + "");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			jsonData = in.readLine();
			if (jsonData != null) {
				employee = JsonToJavaUtil.JsonToJava(jsonData, EmployeeUpdate.class);
			} else {
				employee = null;
			}
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	private String getMonthName(int month) {
		switch (month) {
		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";
		default:
			return "";
		}
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getUtilJSON() {
		return utilJSON;
	}

	public void setUtilJSON(String utilJSON) {
		this.utilJSON = utilJSON;
	}

	public UtilizationYear getUtilizationYear() {
		return utilizationYear;
	}

	public void setUtilizationYear(UtilizationYear utilizationYear) {
		this.utilizationYear = utilizationYear;
	}

	public void validate() {
		if (getActionName().equals("getCalendarLink")) {
			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			startCal.set(Integer.parseInt(year.getStartDate().substring(0, 4)),
					Integer.parseInt(year.getStartDate().substring(5, 7)) - 1,
					Integer.parseInt(year.getStartDate().substring(8, 10)), 0, 0);
			endCal.set(Integer.parseInt(year.getEndDate().substring(0, 4)),
					Integer.parseInt(year.getEndDate().substring(5, 7)) - 1,
					Integer.parseInt(year.getEndDate().substring(8, 10)), 0, 0);

			if (startCal.after(endCal)) {
				addActionError("From Date should be before To Date");
			}

			EmployeeUpdate employee = getEmployeeDetails(
					ActionContext.getContext().getSession().get("employeeIdNumber") + "");
			Calendar rollInDate = Calendar.getInstance();
			Calendar rollOffDate = Calendar.getInstance();
			rollInDate.set(Integer.parseInt(employee.getStartDate().substring(0, 4)),
					Integer.parseInt(employee.getStartDate().substring(5, 7)) - 1,
					Integer.parseInt(employee.getStartDate().substring(8, 10)) - 1, 0, 0);
			rollOffDate.set(Integer.parseInt(employee.getEndDate().substring(0, 4)),
					Integer.parseInt(employee.getEndDate().substring(5, 7)) - 1,
					Integer.parseInt(employee.getEndDate().substring(8, 10)), 0, 0);

			if (startCal.before(rollInDate) || startCal.after(rollOffDate) || endCal.before(rollInDate)
					|| endCal.after(rollOffDate)) {
				addActionError("From Date and To Date should be with in your Roll-in and Roll-off Date");
				addActionError("Roll-in Date: " + employee.getStartDate() + " Roll-off Date: " + employee.getEndDate());
			}
		} else if (getActionName().equals("saveUtilizationLink")) {
			boolean valid = true;
			for (Month month : year.getMonthList()) {
				for (Week week : month.getWeeks()) {
					for (Day day : week.getDays()) {
						if (day.getDate() != 0 && day.getHours() != null && (day.getHours().equals("") == false)) {
							try {
								if (Integer.parseInt(day.getHours()) < 0 || Integer.parseInt(day.getHours()) > 24) {
									addActionError("Utilization Hours should be between 0-24");
								}
							} catch (NumberFormatException ne) {
								List<String> utilType = Arrays.asList("VL", "SL", "EL", "OL", "TR", "HO", "CDO");
								if (utilType.contains(day.getHours().toUpperCase()) == false) {
									valid = false;
								} else {
									day.setHours(day.getHours().toUpperCase());
								}
							}
						}
					}
				}
			}
			if (valid == false) {
				addActionError("Only VL, SL, EL, OL, TR, HO, CDO are accepted");
			}
		}
	}

	public String getActionName() {
		return ActionContext.getContext().getName();
	}

}
