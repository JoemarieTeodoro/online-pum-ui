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
<form action="getCalendarLink" class="ibm-column-form ibm-styled-form" enctype="multipart/form-data" method="post">

	<p style="padding: 3px">
		<label for="startDate" style="margin: 0px">From<span class="ibm-required">*</span></label>
		<span><input id="startDate" class="ibm-date-picker" required="required" name="year.startDate" type="text" width="137px"/></span>
	</p>
	
	<p style="padding: 3px">
		<label for="endDate" style="margin: 0px">To<span class="ibm-required">*</span></label>
		<span><input id="endDate" class="ibm-date-picker"  required="required"  name="year.endDate" type="text"/></span>
	</p>		
	
	<div class="ibm-buttons-row" align="right" style="width: 343px;">
		<p>
			<input value="Submit" type="submit"
				name="submit" class="ibm-btn-small" /> 
		</p>
	</div>
</form>
