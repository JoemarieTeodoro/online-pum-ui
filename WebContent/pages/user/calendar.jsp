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
	// raw data that will be passed to backend part
	var leaveData = [];
	// this will act as mapper to leaveData so that when entries are back to default 
	// we can remove it
	var leaveEntries = [];
	var holidayVault = [];
	var defaultEventVault = [];	
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
			events: <s:property value="events" escape="false"/>
		});
	});
	
	function createLeave(event) {
		if (<s:property value="locked"/> == true) {
			return;
		}
		var leaveName = prompt('Request for:');
		leaveName = leaveName ? leaveName.toUpperCase() : leaveName;
		
		if (leaveName && event.holiday && !isValidHolidayEntry(leaveName)) {
			alert("Invalid holiday entry.");
			return;
		}
		
		if (leaveName && !event.holiday && !isValidEntry(leaveName)) {
			alert("Invalid leave entry.");
			return;
		}

		$('#calendar').fullCalendar('updateEvent', createLeaveEvent(event, leaveName));
		document.leaveForm.elements['leaveEntry'].value = leaveData;
		enableSubmitButton();
	}

	function enableSubmitButton() {
		var isHidden = leaveEntries.length > 0 ? false : true;
		$(':input[type="submit"]').prop('disabled', isHidden);
	}
	
	function createLeaveEvent(event, leaveName) {
		var holidays = holidayVault.filter(function(eventEntry) {
			return eventEntry.date == event.date;
		});
		
		var defaults = defaultEventVault.filter(function(eventEntry) {
			return eventEntry.title == leaveName && eventEntry.date == event.date;
		});
		
		if (leaveName == 'HO' && holidays.length) {
			event = updateEvent(holidays, event);
		} else if (defaults.length) {
			event = updateEvent(defaults, event);
		} else {
			backupEvents(event, leaveName);
			event.title = leaveName;
			event.color = "DarkOliveGreen";
			
			leaveData.push(createLeaveDataEntry(event, leaveName));
			leaveEntries.push(event.date);
		}
		return event;
	}
	
	function updateEvent(updatedEvent, event) {
		var index = leaveEntries.indexOf(event.date);
		leaveData.splice(index, 1);
		leaveEntries.splice(index, 1);
		return updatedEvent[0];
	}
	
	function backupEvents(event, leaveName) {
		if (leaveName != 'HO' && event.holiday) {
			holidayVault.push(Object.assign({}, event));
		} else {
			defaultEventVault.push(Object.assign({}, event));
		}
	}
	
	function createLeaveDataEntry(event, leaveName) {
		var splittedCurrDate = new Date().toLocaleDateString().split('/');
		var leaveStatus = Number(leaveName) ? "approved" : "pending";
		
		return JSON.stringify({
			"leaveName" : Number(leaveName) ? "RC" : leaveName,
			"date" : event.date,
			"employeeID" : <s:property value="employeeID"/>,
			"employeeLeaveID": event.employeeLeaveID,
			"yearID" : <s:property value="yearID"/>,
			"value": Number(leaveName) ? leaveName : 0,
			"status" : leaveStatus,
			"createDate" : getFixedDate(splittedCurrDate),
			"updateDate" : getFixedDate(splittedCurrDate)
		});
	}
	
	function isValidHolidayEntry(leaveEntry) {
		return leaveEntry == 'HO' || isValidNumberRange(leaveEntry);
	}
	
	function isValidNumberRange(leaveEntry) {
		return Number(leaveEntry) && leaveEntry >= 8 && leaveEntry <= 24;
	}
	
	function isValidEntry(leaveEntry) {
		var isValidLeave = leaveEntry == 'VL' || leaveEntry == 'OL' || 
			leaveEntry == 'CDO' || leaveEntry == 'EL';
		return isValidNumberRange(leaveEntry) || isValidLeave;
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
	
	function warnUser() {
		var warn = prompt("Please be informed that once you submit your PUM Entry, Editing or Updating will be limited to the current quarter unless proper Authorization has been granted by the Responsible Person. Continue? (Y/N)");
		if (warn && warn.toUpperCase() == 'Y') {
			return true;
		}
		return false;
	}
</script>
<p>*To revert holiday entry, type 'HO' on that entry.</p>
<br>
<s:form action="leaveDraftLink" method="post" name="leaveForm">
	<s:hidden name="leaveEntry"/>
	<s:submit name="submit" id="submit" key="Submit" disabled="true" accesskey="S" onClick="return warnUser()"/>
</s:form>
</body>