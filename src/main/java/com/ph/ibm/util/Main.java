package com.ph.ibm.util;

import java.time.LocalDateTime;

import com.ph.ibm.model.UtilizationJson;
import com.ph.ibm.model.UtilizationYear;

public class Main {

	public static void main(String[] args) {

		String jsonEmp = "{\"year\": 2018, \"utilization_JSON\": [{\"month\": 9,\"day\": 1,\"dayOfMonth\": 30,\"utilizationHours\": \"9:00\",\"editable\": \"\"},"
				+ "												  {\"month\": 3,\"day\": 2,\"dayOfMonth\": 2,\"utilizationHours\": \"8 \",\"editable\": \"\"}]}";
		UtilizationYear utilizationYear = JsonToJavaUtil.JsonToJava(jsonEmp, UtilizationYear.class);

		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		System.out.println("");
		System.out.println("CURRENT YEAR: " + year + " CURRENT MONTH: " + month + " CURRENT DAY OF MONTH: " + day);
		int counter = 0;
		for (UtilizationJson json : utilizationYear.getUtilizationJSON())
			if (utilizationYear.getYear() >= year && json.getMonth() >= month && json.getDayOfMonth() >= day) {
				json.setEditable("E");
				/*System.out.print("POJO YEAR: " + utilizationYear.getYear());
				System.out.print(" POJO MONTH: " + utilizationYear.getUtilization_JSON().get(0).getMonth());
				System.out
						.print(" POJO DAY OF MONTH: " + utilizationYear.getUtilization_JSON().get(0).getDayOfMonth());
				System.out.print(" POJO HOURS: " + utilizationYear.getUtilization_JSON().get(0).getUtilizationHours());
				System.out.println(" EDITABLE: " + utilizationYear.getUtilization_JSON().get(counter).getEditable());*/

				counter++;
			} else {
				json.setEditable("D");
				/*System.out.print("POJO YEAR: " + utilizationYear.getYear());
				System.out.print(" POJO MONTH: " + utilizationYear.getUtilization_JSON().get(1).getMonth());
				System.out
						.print(" POJO DAY OF MONTH: " + utilizationYear.getUtilization_JSON().get(1).getDayOfMonth());
				System.out.print(" POJO HOURS: " + utilizationYear.getUtilization_JSON().get(1).getUtilizationHours());
				System.out.println(" Editable: " + utilizationYear.getUtilization_JSON().get(counter).getEditable());*/
				counter++;
			}

		String finalJson = JavaToJsonUtil.JavaToJson(utilizationYear);
		System.out.println(finalJson);

	}
}
