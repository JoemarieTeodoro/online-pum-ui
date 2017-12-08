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
	private Logger logger = Logger.getLogger(EventsCreator.class);
	private String startDate, endDate;
	
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
			if (startDate == null) {
				startDate = "'" + empEvent.getCurrFYStartDate() + "'";
			}
			if (endDate == null) {
				endDate = "'" + empEvent.getCurrFYEndDate() + "'";
			}
			// check if type is a number so that it will be colored as gray
			if (employeeLeave.getLeaveName().matches("\\d+")) {
				if (Integer.parseInt(employeeLeave.getLeaveName()) > 8) {
					eventMapper.setColor("OrangeRed");
				} else {
					eventMapper.setColor("Gray");
				}
			}
			if (employeeLeave.isHoliday()) {
				eventMapper.setColor("DarkCyan");
			}
			try {
				events.add(mapper.writeValueAsString(eventMapper));
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return events;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
}
