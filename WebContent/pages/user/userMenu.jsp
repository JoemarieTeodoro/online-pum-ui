<%@taglib uri="/struts-tags" prefix="s"%>
<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>">

<!-- NAVIGATION_BEGIN-->

<head>

	<style>
	.dropbtn {
	    border: none;
	    cursor: pointer;
	}
	
	.dropdown-content {
	    display: none;
	    position: absolute;
	    background-color: #f9f9f9;
	    min-width: 160px;
	    overflow: auto;
	    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	    z-index: 1;
	    left: 120px;
	}
	
	.dropdown a:hover {background-color: #f1f1f1}
	
	.show {display:block;}
	</style>

</head>

<div id="ibm-navigation" class="ibm-parent">
	<h2 class="ibm-access">Content navigation</h2>
	<ul id="ibm-primary-links">
		<li id="ibm-parent-link"><a href="<s:url action="../user/userLink"/>">User Home</a></li>
		<li id="ibm-overview"><a>Menu</a></li>
		<li>

				<a href="<s:url action="calendarLink"/>">My Hours</a> 
				<div class = "ibm-parent">
				    <a href="#" onclick = "displayDropDown()" class = "dropbtn" >My Utilization Summary</a>
				        <div id  = myDropdown class = "dropdown-content">
				             <a href="<s:url action="inputYearLink"/>">Forecasted Utilization</a>
			                 <a href="#">Actual Utilization</a>
				        </div>
				</div>
				<a href="<s:url action="showAllHolidaysLink"/>">Show Holidays</a>  
				<s:if test="#session.isTeamLead == true">
					<a href="<s:url action="showForApprovalLink"/>">For Approval</a>
				</s:if>				

			
		</li>
	</ul>

</div>
<script>var secondsBeforeExpire = ${pageContext.session.maxInactiveInterval};</script>
<script src="../resources/js/session/sessionExpire.js" type="text/javascript"></script>


<script>

function displayDropDown() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

</script>
<!-- NAVIGATION_END  -->