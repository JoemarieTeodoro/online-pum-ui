<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="./css/message.css" rel="stylesheet" type="text/css">
<title>View Employee</title>
<body>
	<s:if test="hasActionMessages()">
		<div class="successMsg">
			<s:actionmessage/>
		</div>
	</s:if>

	<div class="ibm-title">
	
		<s:if test="#session.role == 'SYS_ADMIN'">
			<h2>Upload Admin List</h2>
		</s:if>
	
		<s:if test="#session.role == 'ADMIN'">
			<h2>Upload Employee List</h2>
		</s:if>
	
	<hr>
	</div>
	
<div>
	<form action="http://localhost:9090/onlinePUM/webapi/opum/dataLoading"
		method="POST" enctype="multipart/form-data" onsubmit="return validation(this)">
		<input type="file" name="file" accept=".csv"> <input
			type="submit" value="Process">
	</form>
</div>
<div id="valid_msg"/></div>
<script src="../resources/js/fileValidation.js"></script>
</body>
