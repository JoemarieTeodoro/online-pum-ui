<%@ page contentType="charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="ibm-title">
	<h2>Enter My Hours</h2>
</div>
<hr>
<s:if test="hasActionErrors()">
	<div class="isa_error" style="width: 598px;">
		<s:actionerror />
	</div>
</s:if>
<br>
<head>
<link href='../resources/js/calendar/fullcalendar.min.css'
	rel='stylesheet' />
<link href='../resources/js/calendar/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
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
	var locked = <s:property value="locked"/>;
	
	$(document).ready(function() {
		document.leaveForm.elements['draft'].value = false;
		if (<s:property value="startFYDate"/> == '' || <s:property value="endFYDate"/> == '') {
			alert("Fiscal year is not defined!");
			document.getElementById('submit').style.display = "none";
			document.getElementById('save').style.display = "none";
			document.getElementById('info').style.display = "none";
			return;
		};
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title'
			},
			navLinks: false, // can click day/week names to navigate views
			selectHelper: true,
// 			validRange: {
// 		        start: <s:property value="startFYDate"/>,
// 		        end: <s:property value="endFYDate"/>
// 		    },
		    eventClick: createLeave,
			events: <s:property value="events" escape="false"/>
		});
		populateInitialLeaveData();
	});
	
	function populateInitialLeaveData() {
		var events = $('#calendar').fullCalendar('clientEvents');
		events.map(function(event) {
			if (event.status && event.status.toLowerCase() === "draft") {
				leaveData.push(createLeaveDataEntry(event, event.title));
				leaveEntries.push(event.date);
			}	
		});
		document.leaveForm.elements['leaveEntry'].value = leaveData;
		enableSubmitButton();
	}
	
	function unlockCalendar() {
		if(confirm("You are about to unlock your PUM entry, please note that all changes will require your TL's approval. Click Ok to proceed otherwise, Cancel.")) {
			locked = false;
		}
	}

	function saveChanges() {
		//alert("Entries saved.");
		document.leaveForm.elements['draft'].value = true;
	}
		
	function createLeave(event) {
		var leaveName = prompt('Request for:');
		leaveName = leaveName ? leaveName.toUpperCase() : leaveName;
		if (!isLeaveEntryValid(event, leaveName)) {
			return;
		}
		
		$('#calendar').fullCalendar('updateEvent', createLeaveEvent(event, leaveName));
		document.leaveForm.elements['leaveEntry'].value = leaveData;
		enableSubmitButton();
	}

	function isLeaveEntryValid(event, leaveName) {
		if (!leaveName || (leaveName == event.title)) {
			return false;
		}
		if (<s:property value="recoverable"/> == false && Number(leaveName) && leaveName > 8) {
			alert("You are not allowed to update your PUM as you are not part of a Recoverable Team.");
			return false;
		}
		if (event.holiday && !isValidHolidayEntry(leaveName, event)) {
			alert("Invalid holiday entry.");
			return false;
		}
		
		if (!event.holiday && !isValidEntry(leaveName)) {
			alert("Invalid leave entry.");
			return false;
		}
		return true;
	}
	
	function enableSubmitButton() {
		var isDisabled = leaveEntries.length > 0 ? false : true;
		$('#submit').prop('disabled', isDisabled);
// 		$('#save').prop('disabled', isDisabled);
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
			backupEvents(event);
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
	
	function backupEvents(event) {
		if (event.holiday) {
			holidayVault.push(Object.assign({}, event));
		} else {
			defaultEventVault.push(Object.assign({}, event));
		}
	}
	
	function createLeaveDataEntry(event, leaveName) {
		var splittedCurrDate = new Date().toLocaleDateString().split('/');
		
		return JSON.stringify({
			"leaveName" : Number(leaveName) > 8 ? "RC" : leaveName,
			"date" : event.date,
			"employeeID" : <s:property value="employeeID"/>,
			"employeeLeaveID": event.employeeLeaveID,
			"yearID" : <s:property value="yearID"/>,
			"value": Number(leaveName) ? leaveName : 0,
			"status" : "Approved",
			"createDate" : getFixedDate(splittedCurrDate),
			"updateDate" : getFixedDate(splittedCurrDate)
		});
	}
	
	function isValidHolidayEntry(leaveEntry, event) {
		return (leaveEntry == 'HO' && Number(event.title)) || isValidNumberRange(leaveEntry);
	}
	
	function isValidNumberRange(leaveEntry) {
		return Number(leaveEntry) && leaveEntry >= 8 && leaveEntry <= 24;
	}
	
	function isValidEntry(leaveEntry) {
		var isValidLeave = leaveEntry == 'VL' || leaveEntry == 'OL' || 
			leaveEntry == 'CDO';
		return isValidNumberRange(leaveEntry) || isValidLeave;
	}
	
	function getFixedDate(splittedDate) {
		var delimeter = "/";
		splittedDate = splittedDate.map(function(dateEntry) {
			if (dateEntry.length < 2) {
				dateEntry = "0" + dateEntry;
			}
			return false;
		});
		return false;
	}
	
	function warnUser() {
		var warn = confirm("Please be informed that once you submit your PUM Entry, Editing or Updating will be limited to the current quarter unless proper Authorization has been granted by the Responsible Person.");
		if (warn && warn.toUpperCase() == 'OK') {
			return true;
		}
		return false;
	}
</script>
<p id="info">
	*To revert holiday entry, type 'HO' on that entry.<br />
	*To file a VL, Enter "VL"<br />
	*To file an OL, Enter "OL"<br />
	*To file a CDO, Enter "CDO"
</p>
	<s:form action="leaveDraftLink" method="post" name="leaveForm">
		<s:hidden name="leaveEntry" />
		<s:hidden name="draft" />
		<br>

		<div class="fc-left">
			<button type="submit"
				id="submit"
				onclick="return warnUser()">Submit</button>
			<br> <br>
<!-- 			<button type="submit" -->
<!-- 				id="save" -->
<!-- 				onclick="saveChanges()">Save Changes</button> -->
		</div>
	</s:form>
</body>