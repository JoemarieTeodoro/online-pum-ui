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
	<h2>Upload Employee List</h2>
	<hr>
	</div>
	
<div>
	<form action="http://localhost:9090/onlinePUM/webapi/opum/dataLoading"
		method="POST" enctype="multipart/form-data">
		<input type="file" name="file" accept=".xlsx, .xls, .csv"> <input
			type="submit" value="Process">
	</form>
</div>
</body>
