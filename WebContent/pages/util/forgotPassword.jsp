<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="../resources/system/css/password.css" />

<s:head/>
<div class="formPasswordStyle">
	<form action="../forgotPassword/validateForgotPasswordLink" method="post" enctype="application/x-www-form-urlencoded">
		<h1>Forgot Password</h1>
		<div class="inset">
			<p>
				<br>
				<s:if test="hasActionErrors()">
				<div style="margin-left: 10px;">
						<s:actionerror cssStyle="list-style: none;"/>
				</div>
				</s:if>

				<label for=email>Email: </label>
                <s:textfield name="email" required="true" width= "248px" />
                <s:submit name="submit" key="Send Email" onclick="getName()" />

				<br>
				<br>
				
			</p>
		</div>
	</form>
</div>
