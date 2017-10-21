
<div class="ibm-title">
	<h2>Employee Roll-in & Roll-off Date</h2>
</div>
</body>
<hr>

<div>
	<form name="myForm" id="myForm" method="post" >
		<p>
			<label for="employeeIdNumber" style="margin: 0px">Employee
				ID:<span class="ibm-required">*</span>
			</label> <span><input id="employeeIdNumber" required="required"
				name="employeeIdNumber" type="text" width="137px" /></span>
		</p>
		<p>
			<label for="projectName" style="margin: 0px">Project Name:<span
				class="ibm-required">*</span></label> <span><input id="projectName"
				required="required" name="projectName" value="USAA" type="text"
				width="137px" /></span>

		</p>
		<p>
			<label for="startDate" style="margin: 0px">Start Date:<span
				class="ibm-required">*</span></label> <span><input id="startDate"
				class="ibm-date-picker" required="required" name="startDate"
				type="text" width="137px" /></span>
		</p>

		<p>
			<label for="endDate" style="margin: 0px">End Date:<span
				class="ibm-required">*</span></label> <span><input id="endDate"
				class="ibm-date-picker" required="required" name="endDate"
				type="text" /></span>
		</p>

		<button id="submit" type="submit" onclick="sendJSON()" >Submit</button>
	</form>
</div>

<script>
	 function formToJSON() {
		
		var st =document.getElementById('startDate').value
		var start = st.split("/");
		var en= document.getElementById('endDate').value
		var end= en.split("/");
		var json = {
			"employeeIdNumber" : document.getElementById('employeeIdNumber').value,
			"projectName" : document.getElementById('projectName').value,
			"startDate" : start[2]+"-"+start[0]+"-"+start[1],
			"endDate" : end[2]+"-"+end[0]+"-"+end[1]
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		  var xhttp = new XMLHttpRequest();	  
		  xhttp.open("POST", "http://localhost:8080/onlinePUM/webapi/opum/saveProjectEngagementDate");
		  xhttp.setRequestHeader("Content-Type", "application/json");
		  xhttp.send(formToJSON());  
		 //alert('saving '+formToJSON());
		  alert('Data Successfully Saved ');
		 
	} 
	 
</script>