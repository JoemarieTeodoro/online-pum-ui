function approveReject(url) {
       //Build a collection of your objects
       var YourObjects = [];

       //Populate each of your checked values into the collection
       $('[name="YourCheckBoxes"]:checked').each(function () {
           //Push this object into your YourObjects collection
    	   
    	   var checkboxId = $(this).attr('id');
    	   var employeeId = trimStringInput($('#' + checkboxId + 'Id').text());
    	   var fullname = trimStringInput($('#' + checkboxId + 'fullname').text());
    	   var status = "";
    	   var leaveDate = trimStringInput($('#' + checkboxId + 'leaveDate').text());
    	   var leaveType = trimStringInput($('#' + checkboxId + 'leaveType').text());
    	   var createDate = trimStringInput($('#' + checkboxId + 'createDate').text());
    	   var updateDate = "";
    	   var teamLeadEmployeeId = "";
    	   var leaveId = trimStringInput($('#' + checkboxId + 'leaveId').text());
    	   leaveId = parseInt(leaveId);
    	   
    	   var employee = {
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
    	   
    	   YourObjects.push(employee);
       });

       //Submit through jQuery
       $.ajax({
           url: url,
           type: 'POST',
           traditional: true,
           dataType: 'json',
           data: JSON.stringify(YourObjects),
           contentType: 'application/json; charset=utf-8',
           success: function (result) {
               //Post was successful!
        	   alert("The leaves were approved");
           }
       }); 
}

function approve(){
	var url = $('#file').attr('action');
	approveReject(url+ '/approve');
}

function reject(){
	var url = $('#file').attr('action');
	approveReject(url+ '/reject');
}

function trimStringInput(strInput)  {
	strInput = strInput.trim();
	strInput = strInput.replace(/\r?\n|\r/,"");
	return strInput.replace(/\r?\n|\r/,"");
}