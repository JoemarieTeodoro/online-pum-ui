<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="ibm-title">
	<h2>Search Employee</h2>
	</div>
	<hr>
	
	<s:textfield size="50" id="searchEmployeeIdNumber" name="searchEmployeeIdNumber" required="true" label="Employee ID "/> 
	<input value="Search"
	class="ibm-btn-pri" type="submit" onclick="getdetails()"
	id="submitButton">


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
	
<input value="Update" name=<s:property value="#session.update_action"/>
	class="ibm-btn-pri" type="submit" onclick="sendJSON()"
	id="updateButton">	
 <script>
    var xmlhttp;
    function init() {
       // put more code here in case you are concerned about browsers that do not provide XMLHttpRequest object directly
       xmlhttp = new XMLHttpRequest();
    }
    function getdetails() {
    	xmlhttp = new XMLHttpRequest();
        var empno = document.getElementById("searchEmployeeIdNumber").value;
        var link = "<%=session.getAttribute("form_action")%>"
		var url = link + empno
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
                    	document.getElementById("projectName").disabled = true;
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
    	//for input validation
		var employeeIdNumber = document.getElementById('employeeIdNumber').value;
		var fullName = document.getElementById('fullName').value;
		var empEmail = document.getElementById('email').value; 
		var projectName = document.getElementById('projectName').value;
		var startDate = document.getElementById('startDate').value;
		var endDate = document.getElementById('endDate').value;
		
		var isValidInput = (isValidText(employeeIdNumber) && isValidText(fullName) && isValidText(startDate) && isValidText(endDate))
			
		if (isValidInput == true){
			var json = {			
					"employeeIdNumber" : employeeIdNumber,
					"fullName" : fullName,
					"email" : empEmail,
					"projectName" : projectName,
					"startDate" : startDate,
					"endDate" : endDate
				};
			var jsonString = JSON.stringify(json);
			return jsonString;
		}else{
			alert("PLS REMOVE SPECIAL CHARACTERS IN SOME FIELDS");
			return null;
		}
	 }
	 function sendJSON(){
		var xhttp = new XMLHttpRequest();
		var link = document.getElementById("updateButton").name;
		xhttp.open("PUT", link);
		xhttp.setRequestHeader("Content-Type", "application/json");
		  if (formToJSON() != null){
			  xhttp.send(formToJSON());  
			  alert('Updated Employee Information! ');
		  }
	}
	function isValidText(str){
		 return !/[\`|\~|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\+|\=|\[|\{|\]|\}|\||\\|\'|\<|\,|\.|\>|\?|\/|\""|\;|\:]/g.test(str);
	}
  </script>
