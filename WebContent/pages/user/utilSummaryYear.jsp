<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
			<div class="ibm-title">
				<h2>Utilization Summary</h2>
				<hr>
			</div>
			
<form action="utilizationSummaryLink" method="get"  style="padding-left:10px" enctype="application/x-www-form-urlencoded">
	<label>Enter PUM year:</label>
	<s:textfield name="year" required="true"/>
	<p>
			<input value="View Utilization Summary" type="submit"
				name="submit" class="ibm-btn-small" /> 
		</p>
</form>
</body>
</html>