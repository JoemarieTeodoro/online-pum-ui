var PERIOD_KEY_SELECT_VALUES = { "": "", "Monthly": "Monthly", "Weekly": "Weekly", "Quarterly": "Quarterly" };
var MONTHS = { "1" : "January", "2" : "February", "3" : "March", "4" : "April", "5" : "May", "6" : "June", "7" : "July",
			   "8" : "August", "9" : "September", "10" : "October",  "11" : "November", "12" : "December" };
var QUARTERS = { "1" : "First Quarter", "2" : "Second Quarter", "3" : "Third Quarter", "4" : "Fourth Quarter"};

$(document).ready(function() {
	setDefaultValuePeriodSelectBox();
	$('#dowloadPumBtn').click(function(){
		downloadPumAjax();
	});
	$('#period').change(function() {
		changePeriodValueSelectBox();
	});
	
});

function setDefaultValuePeriodSelectBox() {
	$.each(PERIOD_KEY_SELECT_VALUES, function(key, value) {
		$('#period').append($('<option>', { value : key }).text(value));
	});
}

function changePeriodValueSelectBox() {
	var periodKey = $('#period').val();
	$('#periodValue').find('option').remove();
	var periodValuesJson = selectPeriodValuesJson(periodKey);
	$.each(periodValuesJson, function(key, value) {
		$('#periodValue').append($('<option>', { value : key }).text(value));
	});
	
}

function selectPeriodValuesJson(periodKey) {
	var periodValueResult = { };
	if (periodKey == "Monthly") {
		periodValueResult = MONTHS;
	} else if (periodKey == "Weekly") {
		periodValueResult = generateWeeksJson();
	} else if (periodKey == "Quarterly") {
		periodValueResult = QUARTERS;
	}
    return periodValueResult;
}

function generateWeeksJson() {
	var resultJson = { "" : "" };
	for (i = 1; i <= 52; i++) {
	  var indexStr = i.toString();
	  resultJson[indexStr] = "Week " + indexStr;
	}
	return resultJson;
}

function downloadPumAjax() {
	var url = $('#file').attr('action');
	var periodKey = $('#period').val();
	var periodValue = $('#periodValue').val();
	var backslash = "/";
	
	if (periodKey || periodValue) {
		window.location.href = url + "?" + "periodKey=" + periodKey + "&periodValue=" +  periodValue;
	} else {
		alert("The Period Key/Value must have a valid input");
	}
}