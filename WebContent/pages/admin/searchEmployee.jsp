<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="ibm-title">
	<h2>Search Employee</h2>
	</div>
	<hr>
	
	<s:textfield size="50" id="searchEmployeeIdNumber" name="searchEmployeeIdNumber" required="true" label="Employee ID "/> 
	<input value="Search" name="submit" class="ibm-btn-pri" type="submit"  onclick="getdetails()">


	<table style="width: 400px" cellspacing="0" cellpadding="0" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
		<caption>
		<em><center>Employee Information</center></em>
		</caption>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Employee Serial No.</center></a></th>
				<td><input type="text" id="employeeIdNumber" name="employeeIdNumber"/></td>
			</tr>	
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Resource Name</center></a></th>
				<td><input type="text" id="fullName" name="fullName"/></td>
			</tr>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Email Address</center></a></th>
				<td><input type="text" id="email" name="email"/></td>
			</tr>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Project</center></a></th>
				<td><input type="text" id="projectName" name="projectName"/></td>
			</tr>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Roll-in Date</center></a></th>
				<td><input type="text" id="startDate" name="startDate"/></td>
			</tr>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Roll-off Date</center></a></th>
				<td><input type="text" id="endDate" name="endDate"/></td>
			</tr>
	</table>
	
	<input value="Update" name="submit" class="ibm-btn-pri" type="submit"  onclick="sendJSON()">
	
 <script>
    var xmlhttp;
    function init() {
       // put more code here in case you are concerned about browsers that do not provide XMLHttpRequest object directly
       xmlhttp = new XMLHttpRequest();
    }
    function getdetails() {
    	xmlhttp = new XMLHttpRequest();
        var empno = document.getElementById("searchEmployeeIdNumber").value;
        var url = "http://localhost:9090/onlinePUM/webapi/opum/searchEmployee/" + empno;
        xmlhttp.open('GET',url, true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {

               var fullName =  document.getElementById("fullName");
               var email =  document.getElementById("email");
               if (xmlhttp.readyState == 4) {
                   if (xmlhttp.status == 200) {
                       var det = JSON.parse(xmlhttp.responseText);
                       	document.getElementById("employeeIdNumber").value = det.employeeIdNumber;
                       	document.getElementById("fullName").value = det.fullName;
                    	document.getElementById("email").value = det.email;
                    	document.getElementById("projectName").value = det.projectName;
                    	document.getElementById("startDate").value = det.startDate;
                       	document.getElementById("endDate").value = det.endDate;
                       	if (det.active == true){
                           	document.getElementById("isActive").value = "Active";
                       	} else{
   							document.getElementById("isActive").value = "Inactive";
   						}  						  	   							
                 }	
                 else
                       alert("Invalid Employee ID Number");
               }
        };
    }
    
    function formToJSON() {
/* 	alert("Success");
	alert("EmpIDNo:" + document.getElementById('employeeIdNumber').value);
	alert("fullName:" + document.getElementById('fullName').value);
	alert("email:" + document.getElementById('email').value);
	alert("Proj:" + document.getElementById('projectName').value);
	alert("Status:" + document.getElementById('isActive').value);
	alert("Start:" + document.getElementById('startDate').value);
	alert("End:" + document.getElementById('endDate').value); */
	var active;
	
	if (document.getElementById('isActive').value === "Active")
	{
		active = true;
	} else {
		active = false;
	}
	
	var json = {			
			"employeeIdNumber" : document.getElementById('employeeIdNumber').value,
			"fullName" : document.getElementById('fullName').value,
			"email" : document.getElementById('email').value,
			"projectName" : document.getElementById('projectName').value,
			"active" : active,
			"startDate" : document.getElementById('startDate').value,
			"endDate" : document.getElementById('endDate').value
		};
		var jsonString = JSON.stringify(json);
		//alert("JSON: "+jsonString);
		 return jsonString;
	 }
	 function sendJSON(){
		  var xhttp = new XMLHttpRequest();
		  xhttp.open("PUT", "http://localhost:9090/onlinePUM/webapi/opum/updateEmployeeDetails");
		  xhttp.setRequestHeader("Content-Type", "application/json");
		  xhttp.send(formToJSON());  
		  alert('Updated Employee Information! ');
	} 	 
  </script>
