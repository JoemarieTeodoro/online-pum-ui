<%@page import="org.apache.catalina.filters.RestCsrfPreventionFilter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.*,java.util.*, com.sun.jersey.api.client.*"%>
<!DOCTYPE html>
<link href="../resources/system/css/style.css" rel="stylesheet"
	type="text/css">
<link href="../resources/system/css/defineMonthEndStyle.css"
	rel="stylesheet" type="text/css">

<s:head />
<script type="text/javascript" src="../resources/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="../resources/js/admin/PUMMonth.js"></script>
<body>
	<div class="ibm-title">
		<h2>
			<s:property value="#session.subtitle" />
		</h2>
	</div>
	<hr>

	<div>
		<form action=<s:property value="#session.form_action"/>
			name=<s:property value="#session.get_PumMonth_Link"/> id="myForm"
			method="post">
			<table id="monthTable" style="width: 100; padding-bottom: 50px;">
				<tr>
					<td><label>Fiscal Year:</label> <select id="yearList">
							<s:iterator value="pumYearList.pumYearList" var="pumYear">
								<option value="<s:property value='%{#pumYear.yearId}'/>">
									<s:property value='%{#pumYear.pumYear}' />
								</option>
							</s:iterator>
					</select></td>
					<td>
				<tr>
					<td><label>January:<span class="ibm-required">*</span></label></td>
					<td id="datePickerCol"><span><input id="january"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>February<span class="ibm-required">*</span></label></td>
					<td id="datePickerCol"><input id="february" type="date"
						required></span></td>
				</tr>
				<tr>
					<td><label>March:<span class="ibm-required">*</span></label></td>
					<td id="datePickerCol"><span><input id="march"
							type="date" required> </span></td>
				</tr>
				<tr>
					<td><label>April:<span class="ibm-required">*</span></label></td>
					<td id="datePickerCol"><span><input id="april"
							type="date" required> </span></td>
				</tr>
				<tr>
					<td><label>May:<span class="ibm-required">*</span></label></td>
					<td id="datePickerCol"><span><input id="may"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>June:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="june"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>July:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="july"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>August:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="august"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>September:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="september"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>October:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="october"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>November:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="november"
							type="date" required></span></td>
				</tr>
				<tr>
					<td><label>December:<span class="ibm-required">*</span></label>
					<td id="datePickerCol"><span><input id="december"
							type="date" required></span></td>
				</tr>
				<tr>
					<td align="right" colspan="10"><input type="submit"
						id="submitButton" value="Submit"></td>
				</tr>
				</td>
				</tr>
			</table>
		</form>
	</div>
</body>