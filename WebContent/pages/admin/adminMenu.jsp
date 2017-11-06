<%@taglib uri="/struts-tags" prefix="s"%>
<!-- NAVIGATION_BEGIN-->
<div id="ibm-navigation" class="ibm-parent">
	<h2 class="ibm-access">Content navigation</h2>
	<ul id="ibm-primary-links">
		<li id="ibm-parent-link"><a
			href="<s:url action="../admin/adminHomeLink"/>">Admin Home</a></li>
		<li id="ibm-overview"><a>Menu</a></li>
		<li>
				<a href="<s:url action="uploadCSVLink"/>">Upload Admin List</a>
				<a href="<s:url action="searchEmployeeLink"/>">Search Employee</a>
				<a href="<s:url action="projectEngagementLink"/>">Project Engagement Date</a> 
				<a href="<s:url action="downloadPUMLink"/>">Download PUM</a>
				<a href="<s:url action="definePUMYearLink"/>">Define PUM Year</a>
				<a href="<s:url action="viewPUMYearLink"/>">View PUM Year</a>
				<a href="<s:url action="viewAllPUMYearLink"/>">View All PUM Year</a>
				<a href="<s:url action="defineHolidaysLink"/>">Define Holidays</a>
				<a href="<s:url action="showAllHolidaysLink"/>">Show All Holidays</a>
				<a href="<s:url action="searchHolidayLink"/>">Search Holiday</a>

				<%-- <s:if test="#session.role == 'HR Manager'"> --%>
				
				<a href="<s:url action="adminCalendarLink"/>">My Hours</a>
		</li>
	</ul>

</div>
<!-- NAVIGATION_END  -->