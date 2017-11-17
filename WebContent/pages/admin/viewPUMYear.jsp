<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="ibm-title">
	<h2>Search PUM Year</h2>
	</div>
	<hr>
	
	<s:textfield size="50" id="year" name="year" required="true" label="Enter PUM Year "/> 
	<input value="Search" name="submit" class="ibm-btn-pri" type="submit"  onclick="getdetails()">


	<table style="width: 400px" cellspacing="0" cellpadding="0" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
		<caption>
		<em><center>PUM YearInformation</center></em>
		</caption>
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>PUM Year</center></a></th>
				<td><input type="text" id="pumYear" name="pumYear"/></td>
			</tr>	
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>Start Date</center></a></th>
				<td><input type="text" id="start" name="start"/></td>
			</tr>	
			<tr>
				<th scope="col" class="ibm-sort"><a href="#sort"><center>End Date</center></a></th>
				<td><input type="text" id="end" name="end"/></td>
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
        var year = document.getElementById("year").value;
        var url = "http://localhost:8080/onlinePUM/webapi/opum/yeardate/"+year;
        xmlhttp.open('GET',url, true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {
               if (xmlhttp.readyState == 4) {
                   if (xmlhttp.status == 200) {
                       var det = JSON.parse(xmlhttp.responseText);
                       	document.getElementById("pumYear").value = det.pumYear;
                    	document.getElementById("start").value = det.start;
                       	document.getElementById("end").value = det.end;					  	   							
                 }	
                 else
                       alert("PUM Year not found");
               } 
        };
    }
    
    function formToJSON() {	
	var json = {			
			"pumYear" : document.getElementById('pumYear').value,
			"start" : document.getElementById('start').value,
			"end" : document.getElementById('end').value
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		  var xhttp = new XMLHttpRequest();
		  xhttp.open("PUT", "http://localhost:8080/onlinePUM/webapi/opum/editPUMYearDate");
		  xhttp.setRequestHeader("Content-Type", "application/json");
		  xhttp.send(formToJSON());  
		  alert('Updated PUM Year Information! ');
	} 	 
  </script>