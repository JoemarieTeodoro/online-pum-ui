package com.ibm.opum.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ibm.opum.resourceutils.ClientConfiguration;
import com.ibm.opum.user.bean.EmployeeEvent;
import com.ibm.opum.user.bean.EmployeeLeave;
import com.opensymphony.xwork2.ActionContext;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class EventsCreator {
	private static final String PENDING_STATUS = "pending";
	private static final String ENCLOSED_QUOTATION = "'";
	private Logger logger = Logger.getLogger(EventsCreator.class);
	private String startDate, endDate, empID, yearID, employeeLeaveID;
	private boolean locked, recoverable;
	
	public List<String> createEvents(Client client) {
		String employeeID = (String) ActionContext.getContext().getSession().get("employeeID");

		WebResource webResource = client.resource(ClientConfiguration.getConfigProperties().getProperty("SERVER_URL")
				+ "/online-pum-rest/webapi/opum/employeeLeaveList/" + employeeID);
		EmployeeEvent empEvent = webResource.type(MediaType.APPLICATION_JSON).get(EmployeeEvent.class);

		List<EmployeeLeave> empLeaves = empEvent.getEmpLeaveList();
		List<String> events = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < empLeaves.size(); i++) {
			EmployeeLeave employeeLeave = empLeaves.get(i);
			if (employeeLeave.getLeaveName().isEmpty()) {
				continue;
			}
			EventsMapper eventMapper = new EventsMapper();
			eventMapper.setTitle(employeeLeave.getLeaveName());
			eventMapper.setDate(employeeLeave.getDate());
			eventMapper.setHoliday(employeeLeave.isHoliday());
			if (startDate == null) {
				startDate = ENCLOSED_QUOTATION + empEvent.getCurrFYStartDate() + ENCLOSED_QUOTATION;
			}
			if (endDate == null) {
				endDate = ENCLOSED_QUOTATION + empEvent.getCurrFYEndDate() + ENCLOSED_QUOTATION;
			}
			if (empID == null) {
				empID = ENCLOSED_QUOTATION + employeeLeave.getEmployeeID() + ENCLOSED_QUOTATION;
			}
			if (yearID == null) {
				yearID = ENCLOSED_QUOTATION + employeeLeave.getYearID() + ENCLOSED_QUOTATION;
			}
			recoverable = empEvent.isRecoverable();
			changeEmployeeLeaveStatusColor(employeeLeave, eventMapper);
			eventMapper.setEmployeeLeaveID(Integer.valueOf(employeeLeave.getEmployeeLeaveID()));
			try {
				events.add(mapper.writeValueAsString(eventMapper));
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return events;
	}

	private void changeEmployeeLeaveStatusColor(EmployeeLeave employeeLeave, EventsMapper eventMapper) {
		if (employeeLeave.getLeaveName().matches("\\d+")) {
			// recoverable hours color should be different with pre-plotted 8
			if (Integer.parseInt(employeeLeave.getLeaveName()) > 8) {
				eventMapper.setColor(CalendarColors.DarkCyan.toString());
			} else {
				eventMapper.setColor(CalendarColors.GRAY.toString());
			}
		}
		if (employeeLeave.isHoliday()) {
			eventMapper.setColor(CalendarColors.OrangeRed.toString());
		}
		if (employeeLeave.getStatus() != null && employeeLeave.getStatus().equalsIgnoreCase(PENDING_STATUS)) {
			eventMapper.setColor(CalendarColors.DarkSlateBlue.toString());
			locked = true;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getEmployeeID() {
		return empID;
	}

	public void setEmployeeID(String employeeID) {
		this.empID = employeeID;
	}

	public String getYearID() {
		return yearID;
	}

	public void setYearID(String yearID) {
		this.yearID = yearID;
	}

	public String getEmployeeLeaveID() {
		return employeeLeaveID;
	}

	public void setEmployeeLeaveID(String employeeLeaveID) {
		this.employeeLeaveID = employeeLeaveID;
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

	private enum CalendarColors {

		DarkCyan("DarkCyan"), GRAY("Gray"), OrangeRed("OrangeRed"), DarkSlateBlue("DarkSlateBlue");

		private String colors;

		private CalendarColors(String colors) {
			this.colors = colors;
		}
	}
}
