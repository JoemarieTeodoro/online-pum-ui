var PERIOD_KEY_SELECT_VALUES = { "": "", "Weekly": "Weekly", "Quarterly": "Quarterly", "Yearly": "Yearly",  };
var QUARTERS = { "1" : "First Quarter", "2" : "Second Quarter", "3" : "Third Quarter", "4" : "Fourth Quarter"};
var FISCAL_YEARS = {};

$(document).ready(function() {
	setFiscalYearList();
	setDefaultValuePeriodSelectBox();
	$('#dowloadPumBtn').click(function(){
		downloadPumAjax();
	});
	$('#period').change(function() {
		changePeriodValueSelectBox();
	});
	
});

function setFiscalYearList() {
	var url = $('#file').attr('action');
	url = url.replace("downloadUtilization", "yearList");
	$.ajax({
        url: url,
        type: 'GET',
        traditional: true,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (result) {
        	var fiscalYear = "" + generateFiscalYear(result.pumYearList);
        	FISCAL_YEARS[fiscalYear] = fiscalYear;
        },
        error: function (xhr, ajaxOptions, thrownError) {
          FISCAL_YEARS = { "" : "" };
        }
    }); 
}

function generateFiscalYear(fiscalYears) {
	var resultFiscalYear = 0;
	for (fiscalYearKey in fiscalYears) {
		var fiscalYear = fiscalYears[fiscalYearKey];
		if (resultFiscalYear < fiscalYear.pumYear) {
			resultFiscalYear = fiscalYear.pumYear;
		}
	}
	return resultFiscalYear;
}

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
	if (periodKey == "Yearly") {
		periodValueResult = FISCAL_YEARS;
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
	  resultJson[indexStr] = "Week " + i;
	}
	return resultJson;
}

function downloadPumAjax() {
	var url = $('#file').attr('action');
	var periodKey = $('#period').val();
	var periodValue = $('#periodValue').val();
	
	if (periodKey || periodValue) {
		window.location.href = url + "?" + "periodKey=" + periodKey + "&periodValue=" +  periodValue;
	} else {
		alert("The Period Key/Value must have a valid input");
	}
}