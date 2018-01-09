function approveReject(url, leaveStatus) {
       var employeesArray = [];
       var index = 0;

       $('[name="YourCheckBoxes"]').each(function () {
    	   var updateDate = "";
    	   var teamLeadEmployeeId = "";
    	   var status = "";
    	   var leaveId = $('#leaveId'+index).text();
    	   var employeeId = $('#Id'+index).text();
    	   var fullname = $('#fullName'+index).text();
    	   var leaveDate = $('#leaveDate'+index).text();
    	   var leaveType = $('#leaveType'+index).text();
    	   var createDate = $('#createDate'+index).text();
    	   var usernameEmail = $('#usernameEmail').attr('value');
    	   
    	   var employee = {
    		   "usernameForEmail": usernameEmail,
    		   "employee_Leave_Id": leaveId,
			   "employee_Id": employeeId,
			   "fullName": fullname,
			   "status": status,
			   "leave_Date": leaveDate,
			   "leave_Type": leaveType,
			   "create_Date":createDate,
			   "update_Date": updateDate,
			   "team_Lead_Employee_Id": teamLeadEmployeeId
    	   }
    	   if (this.checked) {
    		   employeesArray.push(employee);
    	   }
    	   index++;
       });

       $.ajax({
           url: url,
           type: 'POST',
           traditional: true,
           dataType: 'json',
           data: JSON.stringify(employeesArray),
           contentType: 'application/json; charset=utf-8',
           success: function (result) {
        	   employeesArray = [];
        	   window.location.reload(true);
        	   alert("The leaves were " + leaveStatus);
           }
       }); 
}

function approve(){
	var url = $('#file').attr('action');
	approveReject(url+ '/approve', 'approved');
}

function reject(){
	var url = $('#file').attr('action');
	approveReject(url+ '/reject', 'rejected');
}

function trimStringInput(strInput)  {
	strInput = strInput.trim();
	strInput = strInput.replace(/\r?\n|\r/,"");
	return strInput;
}