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
				<s:submit name="submit" key="Log In" onclick="getName()" /> 
				
				<br>
				<br>
				
			</p>
		</div>
	</form>
</div>
