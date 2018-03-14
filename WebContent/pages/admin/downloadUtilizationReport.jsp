<%@ page import="org.apache.catalina.filters.RestCsrfPreventionFilter" %>
<%@ page import="com.sun.jersey.api.client.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="../resources/system/css/downloadUtilization.css" />
<link rel="stylesheet" type="text/css" href="../resources/system/css/lib/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css" href="../resources/system/css/lib/jquery-ui.theme.min.css" />
<s:if test="hasActionMessages()">
	<div class="successMsg">
		<s:actionmessage />
	</div>
</s:if>
<div class="ibm-title">
	<h2><s:property value="#session.subtitle" /></h2>
	<hr />
</div>

<div>
	<form
		id="dates"
		action='<s:property value="#session.form_action" />'
		method="post">
		<table id="form-table">
			<tr>
				<td>
					<label>From WE:</label>
				</td>
				<td>
					<input type="text" id="start-we-date" />
				</td>
			</tr>
			<tr>
				<td>
					<label>To WE:</label>
				</td>
				<td>
					<input type="text" id="end-we-date" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Submit" id="submit-btn" />
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="../resources/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="../resources/js/lib/jquery-ui.min.js"></script>
<script type="text/javascript" src="../resources/js/admin/downloadUtilizationReport.js"></script>