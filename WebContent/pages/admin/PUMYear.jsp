<div class="ibm-title">
	<h2>PUM Year Start and End Date</h2>
</div>
<hr>

<div>
	<form action="" name="myForm" id="myForm" method="post" >
		<p>
			<label for="pumYear" style="margin: 0px">Year:<span class="ibm-required">*</span>
			</label> <span><input id="pumYear" required="required"
				name="pumYear" type="text" width="137px" /></span>
		</p>
		<p>
			<label for="start" style="margin: 0px">Start Date:<span
				class="ibm-required">*</span></label> <span><input id="start"
				class="ibm-date-picker" required="required" name="start"
				type="text" width="137px" /></span>
		</p>

		<p>
			<label for="end" style="margin: 0px">End Date:<span
				class="ibm-required">*</span></label> <span><input id="end"
				class="ibm-date-picker" required="required" name="end"
				type="text" /></span>
		</p>

		<button id="submit" type="submit" onclick="sendJSON()" >Submit</button>
	</form>
</div>

<script>
	 function formToJSON() {

		var st =document.getElementById('start').value
		var start = st.split("/");
		var en= document.getElementById('end').value
		var end= en.split("/");
		var json = {
			"pumYear" : document.getElementById('pumYear').value,
			"start" : start[2]+"-"+start[0]+"-"+start[1],
			"end" : end[2]+"-"+end[0]+"-"+end[1]
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		  var xhttp = new XMLHttpRequest();

		  xhttp.open("POST", "http://localhost:9090/onlinePUM/webapi/opum/savePUMYearDate");
		  xhttp.setRequestHeader("Content-Type", "application/json");
		  xhttp.send(formToJSON());  
		 //alert('saving '+formToJSON());
		  alert('Data Successfully Saved! ');
	} 

</script> 