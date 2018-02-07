<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="ibm-title">
	<h2>Search Holiday</h2>
</div>
<hr>

<s:textfield size="50" id="searchName" name="searchName" required="true"
	label="Name " />
<input value="Search" name="submit" class="ibm-btn-pri" type="submit"
	onclick="getdetails()">


<table style="width: 400px" cellspacing="0" cellpadding="0" border="0"
	summary="Data table with alternating rows example"
	class="ibm-data-table ibm-sortable-table ibm-alternating-col">
	<caption>
		<em><center>Holiday Information</center></em>
	</caption>
	<tr>
		<th scope="col" class="ibm-sort"><a href="#sort"><center>Holiday
					Name</center></a></th>
		<td><input type="text" id="name" name="name" /></td>
	</tr>
	<tr>
		<th scope="col" class="ibm-sort"><a href="#sort"><center>Date</center></a></th>
		<td><input type="text" id="date" name="date" disabled = "disabled" /></td>
	</tr>

	<tr>
</table>

<input id="btnUpdate" disabled="disabled" value="Update" name="submit" class="ibm-btn-pri" type="submit"
	onclick="sendJSON()">

<input id="btnDelete" disabled="disabled" value="Delete" name="submit" class="ibm-btn-pri" type="submit"
	onclick="deleteJSON()">

<script src="../resources/js/jquery-2.2.4.js"></script>

<script>
    var xmlhttp;
    function init() {
       // put more code here in case you are concerned about browsers that do not provide XMLHttpRequest object directly
       xmlhttp = new XMLHttpRequest();
    }
    function getdetails() {
    	xmlhttp = new XMLHttpRequest();
        var searchName = document.getElementById("searchName").value;
        var url = "<%=session.getAttribute("form_action")%>" + searchName;
        xmlhttp.open('GET',url, true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {

               var name =  document.getElementById("name");
               if (xmlhttp.readyState == 4) {
                   if (xmlhttp.status == 200) {
                       var det = JSON.parse(xmlhttp.responseText);
                       	document.getElementById("name").value = det.name;
                       	document.getElementById("date").value = det.date.trim(); 
                       	document.getElementById("btnUpdate").disabled = false;
                       	document.getElementById("btnDelete").disabled = false;
                 }	
                   else 
                	   alert("Holiday not found");
               }
               
        
        };
    }
    function formToJSON() {
	var json = {			
			"name" : document.getElementById('name').value,
			"date" : document.getElementById('date').value
		};
		var jsonString = JSON.stringify(json);
		 return jsonString;
	 }
	 function sendJSON(){
		 var link = "<%=session.getAttribute("update_action")%>";
		 var data = formToJSON();
		 $.ajax({
				type : 'POST',
				url : link,
				data : data,
				datatype : 'json',
				contentType : 'application/json',
				processData : false,
				success : function(data) {
					alert(data);
					location.reload();
				},
				error : function(data, jqXHR) {
					alert(data.responseText);
				}
			});
		  
		  
	} 
	 function deleteJSON(){
		 var result = confirm("Are you sure you want to delete this holiday?");
		 if (result) {
			 var link = "<%=session.getAttribute("delete_action")%>";
			  var data = formToJSON();
			  $.ajax( {
				  type : 'POST',
				  url : link,
				  data : data,
				  datatype : 'json',
				  contentType : 'application/json',
				  processData : false,
				  success : function(data) {
					alert(data);
					location.reload();
				  },
				  error : function(data, jqXHR) {
					  alert(data.responseText);
				  }
			  });
		 }
	} 	 
  </script>
