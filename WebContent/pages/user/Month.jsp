<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="../resources/system/css/Month.css" />
<div  style="width: 530px">
<div class="month">
	<ul>
		<li style="text-align: center">
			<s:property value="#month.monthName" /><br> <span style="font-size: 18px">${year.year}</span>
		</li>
	</ul>
</div>

<ul class="weekdays">
	<li>Su</li>
	<li>Mo</li>
	<li>Tu</li>
	<li>We</li>
	<li>Th</li>
	<li>Fr</li>
	<li>Sa</li>
</ul>

<ul class="days">
	<s:iterator value="#month.weeks" var="week" status="weekStatus">
		<s:iterator value="#week.days" var="day" status="dayStatus">
			<li>
				<s:if test="#day.date != 0">
					${day.date}<br>
					<s:textfield name="year.monthList.[monthStatus.index].weeks.[weekStatus.index].days.[dayStatus.index].hours" type="text" style="width: 40px" />
				</s:if>
			</li>
		</s:iterator>
		<%-- <li><s:textfield id="WE1" name="WE1" type="text" style="width: 60px" readonly="readonly" value="%{#week.weekEndingDate}" /></li> --%>
	</s:iterator>
</ul>

</div>
