<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="./css/message.css" rel="stylesheet" type="text/css">
<link href="./css/normalize.css" rel="stylesheet" type="text/css">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<body>
	<form action="update" enctype="multipart/form-data"
		class="ibm-column-form ibm-styled-form" method="post">
				<div class="ibm-title">
					<h2>Employee Information</h2>
				</div>
				<!-- <div class="ibm-container-body">
					<div class="ibm-container ibm-show-hide">
						<h2>
							<a>Personal Information</a>
						</h2> -->
						<s:if test="hasActionMessages()">
							<div class="successMsg">
								<s:actionmessage />
							</div>
						</s:if>
						<div class="ibm-container-body">
							<table style="width: 100%;">
								<tbody>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<s:textfield size="50" name="employee.firstName" value="%{#session.employee.firstName}" label="Firstname "/> 
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<s:textfield size="50" name="employee.middleName" value="%{#session.employee.middleName}" label="M.I. "/> 
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<s:textfield size="50" name="employee.lastName" value="%{#session.employee.lastName}" label="Lastname "/> 
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<s:textfield size="50" name="employee.email" value="%{#session.employee.email}" label="Email Address "/> 
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<s:textfield size="50" name="employee.projectEngagementId" value="%{#session.employee.projectEngagementId}" label="projectEngagementId "/> 
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><s:select value="%{#session.employee.isAdmin}"
												list="%{#{'Admin':'Admin', 'Resource':'Resource'}}"
												name="employee.isAdmin" cssClass="ibm-styled" label="Employee type" />
										</td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr errorFor="employee_rollInDate">
										<td align="center" valign="top" colspan="2"><span
											class="errorMessage"><s:property
													value="rollInDateError"></s:property></span></td>
									</tr>
									<tr>
										<td class="tdLabel"><label for="employee_rollInDate"
											class="label">Roll-in Date</label></td>
										<td><span><input type="text"
												class="ibm-date-picker" id="rollInDate"
												name="employee.rollInDate" value="${employee.rollInDate}" /></span>
										</td>
									<tr>
										<td><br></td>
									</tr>
									<tr errorFor="employee_rollOffDate">
										<td align="center" valign="top" colspan="2"><span
											class="errorMessage"><s:property
													value="rollOffDateError"></s:property></span></td>
									</tr>
									<tr>
										<td class="tdLabel"><label for="employee_rollOffDate"
											class="label">Roll-off Date</label></td>
										<td><span><input type="text"
												class="ibm-date-picker" id="rollOffDate"
												name="employee.rollOffDate" value="${employee.rollOffDate}" /></span>
										</td>
									<tr>
										<td><s:select value="%{#session.employee.isActive}"
												list="%{#{'Active':'Active', 'Inactive':'Inactive'}}"
												name="employee.isActive" cssClass="ibm-styled" label="Status" />
										</td>
									</tr>
								</tbody>
							</table>
							
					<p align="right">
						<input value="Update" name="submit" class="ibm-btn-pri" type="submit"> 
						<input type="submit" name="action:cancelEdit" value="Cancel" class="ibm-btn-pri"/>
					</p>
			</div>
	</form>
</body>