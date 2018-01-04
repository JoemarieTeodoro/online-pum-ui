<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
			<div class="ibm-title">
				<h2>Utilization Summary</h2>
				<hr>
			</div>
	<label>Enter PUM year:</label>
	<s:textfield name="year" required="true" id = "year" />
	<p>
			<input value="View Utilization Summary" type="submit" id = "submit"
				name=<s:property value="#session.employeeID"/> class="ibm-btn-small" onclick= "getdetails()" /> 
		</p>
		
	<table style="width: 400px" cellspacing="0" cellpadding="0" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
        <caption>
        <em><center><s:property value="#session.subtitle"/></center></em>
        </caption>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 1: </center></a></th>
                <td><input type="text" id="quarter1" name="quarter1"/></td>
            </tr>   
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 2: </center></a></th>
                <td><input type="text" id="quarter2" name="quarter2"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 3: </center></a></th>
                <td><input type="text" id="quarter3" name="quarter3"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 4: </center></a></th>
                <td><input type="text" id="quarter4" name="quarter4"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Year-to-date: </center></a></th>
                <td><input type="text" id="ytd" name="ytd"/></td>
            </tr>
    </table>	
		
</body>

 <script>
    function getdetails() {
      var xmlhttp = new XMLHttpRequest();
      var empno = document.getElementById("submit").name;
      var utilYear = document.getElementById("year").value;
      var URI =  "<%=session.getAttribute("form_action")%>"
      var url = URI + empno + "/" + utilYear;
      xmlhttp.open('GET',url, true);
      xmlhttp.send(null);
      xmlhttp.onreadystatechange = function() {
          if (xmlhttp.readyState == 4) {
              if (xmlhttp.status == 200) {
                       var det = JSON.parse(xmlhttp.responseText);
                        document.getElementById("quarter1").value = det.quarter1;
                        document.getElementById("quarter2").value = det.quarter2;
                        document.getElementById("quarter3").value = det.quarter3;
                        document.getElementById("quarter4").value = det.quarter4;
                        document.getElementById("ytd").value = det.ytd;
                        document.getElementById("quarter1").disabled = true;
                        document.getElementById("quarter2").disabled = true;
                        document.getElementById("quarter3").disabled = true;
                        document.getElementById("quarter4").disabled = true;
                        document.getElementById("ytd").disabled = true;
                 }  
               else {
                       alert("ERROR: Fiscal year does not exist!");
                       window.location.reload();
                 }
              }
        };
    }   
  </script>


</html>