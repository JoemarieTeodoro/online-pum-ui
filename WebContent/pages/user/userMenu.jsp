<%@taglib uri="/struts-tags" prefix="s"%>

<!-- NAVIGATION_BEGIN-->

<div id="ibm-navigation" class="ibm-parent">
	<h2 class="ibm-access">Content navigation</h2>
	<ul id="ibm-primary-links">
		<li id="ibm-parent-link"><a href="<s:url action="../user/userLink"/>">User Home</a></li>
		<li id="ibm-overview"><a>Menu</a></li>
		<li>

				<a href="<s:url action="calendarLink"/>">My Hours</a> 
				<a href="<s:url action="inputYearLink"/>">Utilization Summary</a>
				<a href="<s:url action="showAllHolidaysLink"/>">Show Holidays</a>  
				
				<%-- <s:if test="#session.role == 'HR Manager'"> --%>
				
				<%-- </s:if> --%>
			
		</li>
	</ul>

</div>
<!-- NAVIGATION_END  -->