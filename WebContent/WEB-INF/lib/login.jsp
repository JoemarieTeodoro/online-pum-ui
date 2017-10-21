<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="../resources/system/css/loginStyle.css" />

<s:head/>
<div class="formLogInStyle">
	<form action="authenticateLink" method="post" enctype="application/x-www-form-urlencoded">
		<h1>Online PUM</h1>
		<div class="inset">
			<p>
				<br>
				<s:if test="hasActionErrors()">
				<div style="margin-left: 10px;">
						<s:actionerror cssStyle="list-style: none;"/>
				</div>
				</s:if>
				
				<label for="username">Username: </label>
				<s:textfield name="username" required="true" width= "248px" />
				<label for="password">Password: </label>
				<input type="password" name="password" required="required" width= "248px" />
				<s:submit name="submit" key="Log In" /> 
				
				<a href="<s:url action="../register/registerLink"/>">Register user</a>
				<br>
				<br>
				
			</p>
		</div>
	</form>
</div>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="min-height:600px;max-height: 100%;width: 100%;" class="background">
		<br>
		<div style="margin-top: 260px; height: 100px">
			<s:if test="hasActionErrors()">
			<div class="errorMsg">
			<s:actionerror/></div></s:if>
		</div>
		<br><br><br>
		<div style="margin-left: 28px">
			<br>
			<s:form action="home" method="POST" theme="simple">
				<s:textfield name="empNo" required="true" cssStyle="width: 350px"/>
				<br><br><br>
	   			<s:submit name="submit" key="Log In" align="center" cssClass="ibm-btn-small" cssStyle="margin-left: 260px"/>
			</s:form>
		</div>
	</div>
</body>
</html>-->