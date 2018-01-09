<%@page import="org.apache.catalina.filters.RestCsrfPreventionFilter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.*,java.util.*, com.sun.jersey.api.client.*"%>
<link href="../resources/system/css/style.css" rel="stylesheet" type="text/css">
<s:head/>
<script type="text/javascript" src="../resources/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="../resources/js/admin/downloadPUM.js"></script>

<body>
<div class="ibm-container-body">
	<div class="ibm-container ibm-show-hide">
		<h2>Download PUM</h2>
	</div>
</div>
<form id="file" action=<s:property value="#session.form_action" /> >
	<div class="formatDownloadPumDiv">
		<label>Period:</label> 
		<select id="period" class="periodLength"></select>
		<label>Period Value:</label>
		<select id="periodValue" class="periodLength">
		</select>
		<input type="button" id="dowloadPumBtn" value="Download PUM"/>
	</div>
</form>
</body>