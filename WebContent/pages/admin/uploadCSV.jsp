<%@page import="org.apache.catalina.filters.RestCsrfPreventionFilter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.*,java.util.*, com.sun.jersey.api.client.*"%>
<link href="./css/message.css" rel="stylesheet" type="text/css">
<title>View Employee</title>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	function process() {
		var form = $('#file')[0];
		var data = new FormData(form);

		$
				.ajax({
					type : "POST",
					url : "/online-pum-rest/webapi/opum/dataLoading",
					/* dataType : "text", */
					data : data,
					contentType : 'multipart/form-data',
					processData : false,
					contentType : false,
					success : function(data) {
						alert(data);
						window.location.reload();
					},
					error : function(data, textStatus, xhr) {
						alert(data.responseText);
						window.location.reload();
					}
				});
	}
</script>
</head>
<body>
	<s:if test="hasActionMessages()">
		<div class="successMsg">
			<s:actionmessage />
		</div>
	</s:if>

	<div class="ibm-title">
		<h2>Upload Admin List</h2>
		<hr>
	</div>

	<div>
		<table>
			<form id="file" method="POST" enctype="multipart/form-data">
				<td><input type="file" name="file" accept=".csv" /></td>
			</form>

			<td><input type="submit" value="Process" onclick="process()" />
			</td>

		</table>
	</div>

</body>
