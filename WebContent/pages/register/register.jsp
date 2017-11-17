<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="container">
	<form class="ibm-column-form ibm-styled-form"
		action="http://localhost:8080/onlinePUM/webapi/opum/registration"
		method="post" enctype="application/x-www-form-urlencoded">
		<p>
		<label for="employeeIdNumber">Employee ID:</label> 
		<input name="employeeIdNumber" type="text" required="required"/><br>	
		</p>
		<p>	
		<label for="projectName">Project Name:</label>
		<input name="projectName" type="text" required="required"/><br> 
		</p>
		<p>
		<label for="email">Email:</label>
		<input name="email" type="text" required="required"/><br>
		</p>
		<p>
		<label for="password">Password:</label>
		<input name="password" type="password"  id="password" onkeyup="validatePasword" required="required"/><br>
		</p>
		<p>
		<label for="rePass">Confirm password:</label>
		<input name="rePass" type="password" id="rePass" onkeyup="validatePasword" required="required" />
		</p>
		<br>
		<div class="ibm-buttons-row">
		<p>
			<input value="Register" type="submit"
				name="ibm-submit" class="ibm-btn-small" /> 
			<a href="../login/loginLink" class="ibm-btn-small">Cancel</a>
		</p>
	</div>

		
	</form>

	<script>
		var password = document.getElementById("password"), rePass = document
				.getElementById("rePass");

		function validatePassword() {
			if (password.value != rePass.value) {
				rePass.setCustomValidity("Passwords do not match");

			} else {
				rePass.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		rePass.onkeyup = validatePassword;
	</script>
</div>
