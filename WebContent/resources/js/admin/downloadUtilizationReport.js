$(document).ready(function(){
	var datePickerJson = {
		beforeShowDay : function(date){
			return [date.getDay() == 5];
		},
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true
	};
	
	$( "#start-we-date" ).datepicker(datePickerJson);
	$( "#end-we-date" ).datepicker(datePickerJson);
});

$("#submit-btn").click(function(e){
	e.preventDefault();
	var url = $("#dates").attr("action");
	var startWE = $("#start-we-date").val();
	var endWE = $("#end-we-date").val();
	
	window.location.href = url + "?" + "startDate=" + startWE + "&endDate=" +  endWE;
});