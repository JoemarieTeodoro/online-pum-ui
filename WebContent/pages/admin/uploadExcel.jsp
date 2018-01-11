<%@ page import="org.apache.catalina.filters.RestCsrfPreventionFilter" %>
<%@ page import="com.sun.jersey.api.client.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload Excel</title>
	</head>
	<body>
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
		<table>
		<tr> <td> 
			<form 
				id="file" 
				action='<s:property value="#session.form_action" />' 
				method="POST" 
				enctype="multipart/form-data"
				accept-charset="utf-8"
			>
				<input type="file" name="file" accept=".xlsx" />
			</form>
		</td>
		<td>
				<input type="submit" value="Upload" id="upload-btn" />
		</td>
		</tr>
		</table>
		</div>
		<script type="text/javascript" src="../resources/js/jquery-2.2.4.js"></script>
		<script type="text/javascript" src="../resources/js/fileValidation.js"></script>
		<script type="text/javascript" src="../resources/js/admin/uploadExcel.js"></script>
	</body>
</html>