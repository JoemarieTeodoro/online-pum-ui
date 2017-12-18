<%@ page contentType="charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="ibm-title">
	<h2>Enter My Hours</h2>
	</div>
	<hr>
	<s:if test="hasActionErrors()">
		<div class="isa_error" style="width: 598px;">
			<s:actionerror/>
		</div>
	</s:if>
<br>
<head>
<link href='../resources/js/calendar/fullcalendar.min.css' rel='stylesheet' />
<link href='../resources/js/calendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='../resources/js/lib/moment.min.js'></script>
<script src='../resources/js/lib/jquery.min.js'></script>
<script src='../resources/js/calendar/fullcalendar.min.js'></script>
</head>
<body>
<div id='calendar'></div>
<script>
	var leaveData = [];
	$(document).ready(function() {
		if (<s:property value="startFYDate"/> == '' || <s:property value="endFYDate"/> == '') {
			alert("Fiscal year is not defined!");
			document.getElementById('submit').style.display = "none";
			return;
		};
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title'
			},
			defaultDate: <s:property value="startFYDate"/>,
			navLinks: false, // can click day/week names to navigate views
			selectHelper: true,
			validRange: {
		        start: <s:property value="startFYDate"/>,
		        end: <s:property value="endFYDate"/>
		    },
		    eventClick: createLeave,
			eventLimit: true, // allow "more" link when too many events
			events: <s:property value="events" escape="false"/>
		});
	});
	
	function createLeave(event) {
		if (<s:property value="locked"/> == true) {
			return;
		}
		var leaveName = prompt('Request for:');
		leaveName = leaveName ? leaveName.toUpperCase() : leaveName;
		
		var isValidEntry = leaveName
				&& Number(leaveName)
				|| (leaveName == 'VL' || leaveName == 'OL' || 
					leaveName == 'CDO');

		if (leaveName && !isValidEntry) {
			alert("Invalid leave entry.");
			return;
		}

		if (leaveName) {
			event.title = leaveName;
			event.color = "DarkOliveGreen";
			var date = new Date(event.start).toLocaleDateString();
			var splittedDate = date.split('/');
			var splittedCurrDate = new Date().toLocaleDateString().split('/');
			var leaveStatus = Number(leaveName) ? "approved" : "pending";
			
			leaveData.push(JSON.stringify({
				"leaveName" : Number(leaveName) ? "RC" : leaveName,
				"date" : getFixedDate(splittedDate),
				"employeeID" : <s:property value="employeeID"/>,
				"employeeLeaveID": event.employeeLeaveID,
				"yearID" : <s:property value="yearID"/>,
				"value": Number(leaveName) ? leaveName : 0,
				"status" : leaveStatus,
				"createDate" : getFixedDate(splittedCurrDate),
				"updateDate" : getFixedDate(splittedCurrDate)
			}));

			document.leaveForm.elements['leaveEntry'].value = leaveData;
			$('#calendar').fullCalendar('updateEvent', event);
		}
	}

	function getFixedDate(splittedDate) {
		var delimeter = "/";
		splittedDate = splittedDate.map(function(dateEntry) {
			if (dateEntry.length < 2) {
				dateEntry = "0" + dateEntry;
			}
			return dateEntry;
		});
		return splittedDate[0] + delimeter + splittedDate[1] + delimeter
				+ splittedDate[2];
	}
</script>
<br>
<s:form action="leaveDraftLink" method="post" name="leaveForm">
	<s:hidden name="leaveEntry"/>
	<s:submit name="submit" id="submit" key="Submit"/>
</s:form>
</body>