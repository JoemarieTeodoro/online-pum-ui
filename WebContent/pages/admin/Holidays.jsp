
<div class="ibm-title">
	<h2>Enter Holidays</h2>
</div>
<hr>

<div>
	<form action="" name="myForm" id="myForm" method="post" >
		<!-- <p>
			<label for="pumYear" style="margin: 0px">Year:<span class="ibm-required">*</span>
			</label> <span><input id="pumYear" required="required"
				name="pumYear" type="text" width="137px" /></span>
		</p> -->
		<p>  
			<label for="holidayName" style="margin: 0px">Holiday Name:<span class="ibm-required">*</span>
			</label> <span><input id="holidayName" required="required"
				name="holidayName" type="text" width="137px" /></span>
		</p>
		
	<!-- 	<p>
			<label for="holidayDescription" style="margin: 0px">Holiday Description:<span class="ibm-required">*</span>
			</label> <span><input id="holidayDescription" required="required"
				name="holidayName" type="text" width="137px" /></span>
		</p> -->

		<p>
			<label for="date" style="margin: 0px">Date:<span
				class="ibm-required">*</span></label> <span><input id="date"
				class="ibm-date-picker" required="required" name="date"
				type="text" /></span>
		</p>

		<button id="submit" type="submit" onclick="sendJSON()" >Submit</button>
	</form>
</div>

<script>
	 function formToJSON() {
		
		var st =document.getElementById('date').value
		var date = st.split("/");

		var json = {
			//"pumYear" : document.getElementById('pumYear').value,
			"name" : document.getElementById('holidayName').value,
			//"holidayDescription" : document.getElementById('holidayDescription').value,
			"date" : date[2]+"-"+date[0]+"-"+date[1]
			
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		  var xhttp = new XMLHttpRequest();
		  xhttp.open("POST", "http://localhost:9090/onlinePUM/webapi/opum/saveHolidays");
		  xhttp.setRequestHeader("Content-Type", "application/json");
		  xhttp.send(formToJSON());  
		 //alert('saving '+formToJSON());
		  alert('Successfully Saved ');
	} 
	 
</script>