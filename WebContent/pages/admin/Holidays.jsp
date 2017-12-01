<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ibm-title">
	<h2><s:property value="#session.subtitle" /></h2>
</div>
<hr>

<div>
	<form action=<s:property value="#session.form_action" /> name="myForm" id="myForm" method="post" >
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
	</form>
		<button id="submit" type="submit" onclick="sendJSON()" >Submit</button>
	
</div>

<script src="../resources/js/jquery-2.2.4.js"></script>

<script>
	 function formToJSON() {
		
		var st =document.getElementById('date').value
		var date = st.split("/");

		var json = {
			"name" : document.getElementById('holidayName').value,
			"date" : date[2]+"-"+date[0]+"-"+date[1]
			
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		 var link = $(myForm).attr("action");
		  var data = formToJSON();
		  
		  $.ajax({
				type : "POST",
				url : link,
				data : data,
				datatype : "json",
				contentType : 'application/json',
				processData : false,
				success : function(data) {
					alert(data);
				},
				error : function(data, jqXHR) {
					if (jqXHR.status == 500) {
						alert("Invalid Input: Please fill Holiday!");
					} else if (data.status == 500) {
						alert("Invalid Input: Please fill all fields correctly!");
					} else {
						alert(data.responseText);
					}
				}
			});
	} 
	 
</script>