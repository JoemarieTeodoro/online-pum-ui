<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ibm-title">
	<h2><s:property value="#session.subtitle" /></h2>
</div>
<hr>

<div>
	<form action=<s:property value="#session.form_action" /> name="myForm" id="myForm" method="post">
		<p>
			<label for="pumYear" style="margin: 0px">Year:<span class="ibm-required">*</span>
			</label> <span><input id="pumYear" onkeypress="return isNumberKey(event)" required="required"
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
	</form>
		<button id="submit" type="submit" onclick="sendJSON()" >Submit</button>
</div>

<script src="../resources/js/jquery-2.2.4.js"></script>
<script>
     function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : event.keyCode
	    return !(charCode > 31 && (charCode < 48 || charCode > 57));
	 }
	 function formToJSON() {

		var st =document.getElementById('start').value
		var start = st.split("/");
		var en= document.getElementById('end').value
		var end= en.split("/");

		for (var i = 0; i < start.length; i++) {
			if (start[i].length < 2) {
				start[i] = "0" + start[i];
			}
		}
		for (var i = 0; i < end.length; i++) {
			if (end[i].length < 2) {
				end[i] = "0" + end[i];
			}
		}
		var json = {
			"pumYear" : document.getElementById('pumYear').value,
			"start" : start[2]+"-"+start[0]+"-"+start[1],
			"end" : end[2]+"-"+end[0]+"-"+end[1]
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
						alert("Invalid Input: Please fill Fiscal Year!");
					} else if (data.status == 500) {
						alert("Invalid Input: Please fill all fields correctly!");
					} else {
						alert(data.responseText);
					}
				}
			});
	} 

</script> 