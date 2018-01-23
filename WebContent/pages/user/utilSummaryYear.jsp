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
<table>
<tr> 
<td>
	<table style="width: 300px" cellspacing="0" cellpadding="5" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
        <caption>
        <em><center>Forecasted Utilization Summary</center></em>
        </caption>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 1: </center></a></th>
                <td><input type="text" id="forecastQuarter1" name="quarter1"/></td>
            </tr>   
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 2: </center></a></th>
                <td><input type="text" id="forecastQuarter2" name="quarter2"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 3: </center></a></th>
                <td><input type="text" id="forecastQuarter3" name="quarter3"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 4: </center></a></th>
                <td><input type="text" id="forecastQuarter4" name="quarter4"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Year-to-date: </center></a></th>
                <td><input type="text" id="forecastYtd" name="ytd"/></td>
            </tr>
    </table>	
</td>
<td>
    <table style="width: 300px" cellspacing="0" cellpadding="0" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
        <caption>
        <em><center>Actual Utilization Summary</center></em>
        </caption>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 1: </center></a></th>
                <td><input type="text" id="actualQuarter1" name="quarter1"/></td>
            </tr>   
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 2: </center></a></th>
                <td><input type="text" id="actualQuarter2" name="quarter2"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 3: </center></a></th>
                <td><input type="text" id="actualQuarter3" name="quarter3"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 4: </center></a></th>
                <td><input type="text" id="actualQuarter4" name="quarter4"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Year-to-date: </center></a></th>
                <td><input type="text" id="actualYtd" name="ytd"/></td>
            </tr>
    </table>    
    </td>
<td>
    <table style="width: 300px" cellspacing="0" cellpadding="0" border="0" summary="Data table with alternating rows example" class="ibm-data-table ibm-sortable-table ibm-alternating-col">
        <caption>
        <em><center>Combined Utilization Summary</center></em>
        </caption>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 1: </center></a></th>
                <td><input type="text" id="combinedQuarter1" name="quarter1"/></td>
            </tr>   
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 2: </center></a></th>
                <td><input type="text" id="combinedQuarter2" name="quarter2"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 3: </center></a></th>
                <td><input type="text" id="combinedQuarter3" name="quarter3"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Quarter 4: </center></a></th>
                <td><input type="text" id="combinedQuarter4" name="quarter4"/></td>
            </tr>
            <tr>
                <th scope="col" class="ibm-sort"><a href="#sort"><center>Year-to-date: </center></a></th>
                <td><input type="text" id="combinedYtd" name="ytd"/></td>
            </tr>
    </table>    
    </td>
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
                        document.getElementById("forecastQuarter1").value = det.forecastedQuarter1;
                        document.getElementById("forecastQuarter2").value = det.forecastedQuarter2;
                        document.getElementById("forecastQuarter3").value = det.forecastedQuarter3;
                        document.getElementById("forecastQuarter4").value = det.forecastedQuarter4;
                        document.getElementById("forecastYtd").value = det.forecastedYtd;
                        
                        
                        document.getElementById("actualQuarter1").value = det.actualQuarter1;
                        document.getElementById("actualQuarter2").value = det.actualQuarter2;
                        document.getElementById("actualQuarter3").value = det.actualQuarter3;
                        document.getElementById("actualQuarter4").value = det.actualQuarter4;
                        document.getElementById("actualYtd").value = det.actualYtd;
                        
                        
                        document.getElementById("combinedQuarter1").value = det.combinedQuarter1;
                        document.getElementById("combinedQuarter2").value = det.combinedQuarter2;
                        document.getElementById("combinedQuarter3").value = det.combinedQuarter3;
                        document.getElementById("combinedQuarter4").value = det.combinedQuarter4;
                        document.getElementById("combinedYtd").value = det.combinedYtd;
                        
                        //disable boxes
                        document.getElementById("forecastQuarter1").disabled = true;
                        document.getElementById("forecastQuarter2").disabled = true;
                        document.getElementById("forecastQuarter3").disabled = true;
                        document.getElementById("forecastQuarter4").disabled = true;
                        document.getElementById("forecastYtd").disabled = true;
                        document.getElementById("actualQuarter1").disabled = true;
                        document.getElementById("actualQuarter2").disabled = true;
                        document.getElementById("actualQuarter3").disabled = true;
                        document.getElementById("actualQuarter4").disabled = true;
                        document.getElementById("actualYtd").disabled = true;
                        document.getElementById("combinedQuarter1").disabled = true;
                        document.getElementById("combinedQuarter2").disabled = true;
                        document.getElementById("combinedQuarter3").disabled = true;
                        document.getElementById("combinedQuarter4").disabled = true;
                        document.getElementById("combinedYtd").disabled = true;
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