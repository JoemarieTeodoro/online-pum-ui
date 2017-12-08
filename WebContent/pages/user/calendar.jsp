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

	$(document).ready(function() {
		if (<s:property value="startDate"/> == null || <s:property value="endDate"/> == null) {
			alert("Fiscal year is not defined!");
			return;
		};
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title'
			},
			defaultDate: <s:property value="startDate"/>,
			navLinks: false, // can click day/week names to navigate views
			selectHelper: true,
			validRange: {
		        start: <s:property value="startDate"/>,
		        end: <s:property value="endDate"/>
		    },
			select: function(start, end) {
				var title = prompt('Event Title:');
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');
			},
			eventLimit: true, // allow "more" link when too many events
			events: <s:property value="events" escape="false"/>
		});
	});
</script>
</body>