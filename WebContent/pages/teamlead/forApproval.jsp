<%@page import="org.apache.catalina.filters.RestCsrfPreventionFilter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.*,java.util.*, com.sun.jersey.api.client.*"%>
<link href="./css/message.css" rel="stylesheet" type="text/css">
<s:head/>
<script type="text/javascript" src="../resources/js/teamlead/forApproval.js"></script>
<script type="text/javascript" src="../resources/js/jquery-2.2.4.js"></script>

<br/>
<body>
	<form id="file" action=<s:property value="#session.form_action" /> 
						method="POST" >
	<div>
			<span id="usernameEmail" hidden = "hidden" value="<s:property value="#session.fullName" />"></span>
			<table style="width:750px" summary="Data table with alternating rows example" 
			class="ibm-data-table ibm-sortable-table ibm-alternating-col">
			<caption>
				<em>For Approval</em>
			</caption>
			<thead>
				<tr>
						<th scope="col"  width="1%"><span>Select</span><span class="ibm-icon">&nbsp;</span></th>
						<th scope="col" class="ibm-sort" width="10%"><a href="#sort"><span>Employee ID</span><span class="ibm-icon">&nbsp;</span></a></th>
						<th scope="col" class="ibm-sort" width="10%"><a href="#sort"><span>Employee Name</span><span class="ibm-icon">&nbsp;</span></a></th>
						<th scope="col" class="ibm-sort" width="10%"><a href="#sort"><span>Leave Date</span><span class="ibm-icon">&nbsp;</span></a></th>
						<th scope="col" class="ibm-sort" width="10%"><a href="#sort"><span>Leave Type</span><span class="ibm-icon">&nbsp;</span></a></th>
						<th scope="col" class="ibm-sort" width="10%"><a href="#sort"><span>Date Created</span><span class="ibm-icon">&nbsp;</span></a></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="forApprovalList.forApprovalList" var="forApproval" status="incr">
					
					<tr>
						<td><input type="checkbox" id="<s:property value="employee_Id"/>" name="YourCheckBoxes" value="employee_Leave_Id" data-test ="employee_Id" /><span style='visibility:hidden' id="leaveId<s:property value="%{#incr.index}"/>"><s:property value="employee_Leave_Id"/></span></td>
						<td id="Id<s:property value="%{#incr.index}"/>"><s:property value="employee_Id"/></td>
						<td id="fullName<s:property value="%{#incr.index}"/>"><s:property value="fullName"/></td>
						<td id="leaveDate<s:property value="%{#incr.index}"/>"><s:property value="leave_Date"/></td>
						<td id="leaveType<s:property value="%{#incr.index}"/>"><s:property value="leave_Type"/></td>
						<td id="createDate<s:property value="%{#incr.index}"/>"><s:property value="create_Date"/></td>
					</tr>
				</s:iterator>
			</tbody>
			</table>
			
		</div>
		<div>
			<input type="button" onclick="approve()" value="Approve"/>
			<input type="button" onclick="reject()" value="Reject"/>
		</div>
	</form>
</body>



