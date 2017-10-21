<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="../resources/system/css/Month.css" />
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
<form action="saveUtilizationLink" class="ibm-column-form ibm-styled-form" enctype="multipart/form-data" method="post">
	<p style="padding: 4px"></p>
	<s:iterator value="year.monthList" id="monthList" var="month" status="monthStatus">

		<div  style="width: 530px">
		<div class="month">
			<ul>
				<li style="text-align: center">
					<s:property value="#month.monthName" /><br> <span style="font-size: 18px">${year.year}</span>
				</li>
			</ul>
		</div>

		<ul class="weekdays">
			<li>Su</li><li>Mo</li><li>Tu</li><li>We</li><li>Th</li><li>Fr</li><li>Sa</li>
		</ul>
		
		<ul class="days">
			<s:iterator value="#month.weeks" id="weeks" var="week" status="weekStatus">
				<s:iterator value="#week.days" id="days" var="day" status="dayStatus">
					<li>
						<s:if test="#day.date != 0">
							<s:property value="date"/><br>
							<s:if test="#day.isEnabled == false">
								<s:textfield readonly="true" name="year.monthList[%{#monthStatus.index}].weeks[%{#weekStatus.index}].days[%{#dayStatus.index}].hours" type="text" style="width: 40px" />
							</s:if>
							<s:else>
								<s:textfield name="year.monthList[%{#monthStatus.index}].weeks[%{#weekStatus.index}].days[%{#dayStatus.index}].hours" type="text" style="width: 40px" />
							</s:else>
						</s:if>
					</li>
				</s:iterator>
			</s:iterator>
		</ul>
		
		</div>
		<br>
	</s:iterator>
	<br><br>
	<div class="ibm-buttons-row" align="right" style="width: 550px;">
		<p> 	
			<input value="Submit" type="submit"
				name="submit" class="ibm-btn-small" /> 
		</p>
	</div> 
</form>
