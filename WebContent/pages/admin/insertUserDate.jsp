<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="ibm-title">
	<h2><s:property value="#session.subtitle" /></h2>
</div>
	<div>
	<table>
		<form id="form" action=<s:property value="#session.form_action" /> method="post">
			<tr><td>Employee ID: </td><td><input type="text" id="employeeID" required /> </td><br /></tr>
			<tr><td>Leave Name: </td><td><input type="text" placeholder="Type VL, OL, CDO, or recovered hours for the day" size="40" id="leaveName" required /> </td></tr><br />
			<tr><td>Past Date: </td><td><input type="date" id="date" required /> </td></tr><br />
			<tr><td><input type="submit" value="Submit" id="submit" /></td></tr>
		</form> <br />
	</table>
	</div>
<script src="../resources/js/jquery-2.2.4.js"></script>
<script src="../resources/js/admin/insertUserDate.js"></script>
