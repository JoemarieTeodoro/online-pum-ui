var monthsHtmlIDString = ["#january", "#february", "#march",
	"#april", "#may", "#june",
	"#july", "#august", "#september",
	"#october", "#november", "#december"];

var monthsArray = ["January", "February", "March",
	"April", "May", "June",
	"July", "August", "September",
	"October", "November", "December"];

var SAVE_STATUS = "SAVE";
var updateMonthsArr = [];

$(document).ready(function() {	
	$('#submitButton').click(function(e) {
		e.preventDefault();
		var monthsArray = populateMonthsArray();
		var monthsArrayString = JSON.stringify(monthsArray);

		if (SAVE_STATUS == "SAVE") {
			if(isValidDates(monthsArray)){
				saveMonths(monthsArrayString);
			} else {
				alert("Error - You have entered the wrong date input");
			}
		} else if (SAVE_STATUS == "UPDATE") {
			var filteredMonthsArray = filterMonthsArrayForUpdate(monthsArray, updateMonthsArr);
			var updateMonthsStringify = JSON.stringify(filteredMonthsArray);
			if (filteredMonthsArray.length != 0) {
				if(isValidDates(filteredMonthsArray)){
					updateMonths(updateMonthsStringify);
				} else {
					alert("Error - You have entered the wrong date input");
				}
			}
		}
	});

	$('#yearList').change(function() {
		loadMonthsDatePickers();
	});

	loadMonthsDatePickers();

	$('#january').change(function() {
		addUpdateMonths("January");
	});

	$('#february').change(function() {
		addUpdateMonths("February");
	});

	$('#march').change(function() {
		addUpdateMonths("March");
	});

	$('#april').change(function() {
		addUpdateMonths("April");
	});

	$('#may').change(function() {
		addUpdateMonths("May");
	});

	$('#june').change(function() {
		addUpdateMonths("June");
	});

	$('#july').change(function() {
		addUpdateMonths("July");
	});

	$('#august').change(function() {
		addUpdateMonths("August");
	});

	$('#september').change(function() {
		addUpdateMonths("September");
	});

	$('#october').change(function() {
		addUpdateMonths("October");
	});

	$('#november').change(function() {
		addUpdateMonths("November");
	});

	$('#december').change(function() {
		addUpdateMonths("December");
	});

});

function filterMonthsArrayForUpdate(monthsArray, updateMonthsArr) {
	var resultMonthsArray = [];
	for (var i = 0; i < updateMonthsArr.length; i++) {
		for (var j = 0; j < monthsArray.length; j++) {
			if (updateMonthsArr[i] == monthsArray[j].monthName) {
				resultMonthsArray.push(monthsArray[j]);
			}
		}
	}

	return resultMonthsArray;
}

function addUpdateMonths(monthNameVal) {
	if (SAVE_STATUS == "UPDATE") {
		updateMonthsArr = updateMonthsArr.filter(function (elem) {
			return elem != monthNameVal;
		});
		updateMonthsArr.push(monthNameVal);
	}
}

function updateMonths(updateMonths) {
	var updatePUMMonthUrl = $('#myForm').attr('action');
	
	$.ajax({
		url: updatePUMMonthUrl,
		method: 'PUT',
		data: updateMonths,
		dataType: 'json',
		contentType: 'application/json',
		processData: false
	})
	.done(function(data) {
		alert("PUM months are succesfully Updated");
		SAVE_STATUS = "UPDATE";
	})
	.error(function(data) {
		alert("An error occured in updating PUM months");
		SAVE_STATUS = "UPDATE";
	});  
}

function isValidDates(pumMonthsArray) {
	var isValidDates = true;
	for (var i = 0; i < pumMonthsArray.length; i++) {
		if (!isValidDate(pumMonthsArray[i].weekEnd)) {
			isValidDates = false;
			break;
		}
	}
	return isValidDates;
}

function isValidDate(dateString) {
	var regEx = /^\d{4}-\d{2}-\d{2}$/;
	if(dateString.match(regEx) == null) return false;  
	var d = new Date(dateString);
	if(!d.getTime() && d.getTime() !== 0) return false; 
	return d.toISOString().slice(0,10) === dateString;
}


function saveMonths(monthsDataLocal) {
	var savePUMMonthUrl = $('#myForm').attr('action');
	$.ajax({
		url: savePUMMonthUrl,
		method: 'POST',
		data: monthsDataLocal,
		dataType: 'json',
		contentType: 'application/json',
		processData: false
	})
	.done(function(data) {
		alert("PUM months are succesfully saved");
		SAVE_STATUS = "UPDATE";
	})
	.error(function(data) {
		alert("Error - An error occured in saving PUM months");
		SAVE_STATUS = "UPDATE";
	}); 
}

function populateMonthsArray() {	
	var monthsData = [];
	var monthsVal = addMonthVal();
	var months = {};
	var date = new Date();
	var yrID = document.getElementById('yearList').value;
	for(var a = 0; a < monthsArray.length; a++){
		console.log(monthsVal[a]);
		months = {
				monthId :  convertMonthNameToId(monthsArray[a]),
				monthName : monthsArray[a],
				weekEnd : monthsVal[a],
				yearId: yrID
		};
		monthsData.push(months);
	}

	return monthsData;
}

function convertMonthNameToId(monthVal) {
	var monthId;
	switch (monthVal) {
	    case "January":
	    	monthId = 1;
	        break;
	    case "February":
	    	monthId = 2;
	        break;
	    case "March":
	    	monthId = 3;
	        break;
	    case "April":
	    	monthId = 4;
	        break;
	    case "May":
	    	monthId = 5;
	        break;
	    case "June":
	    	monthId = 6;
	        break;
	    case "July":
	    	monthId = 7;
	        break;
	    case "August":
	    	monthId = 8;
	        break;
	    case "September":
	    	monthId = 9;
	        break;
	    case "October":
	    	monthId = 10;
	        break;
	    case "November":
	    	monthId = 11;
	        break;
	    case "December":
	    	monthId = 12;
	        break;
	    default :
	    	monthId = 0;
	        break;
	}
	return monthId;
}


function addMonthVal() {
	var monthsVal =[];
	for(var i = 0; i < monthsHtmlIDString.length; i++){
		monthsVal.push( $(monthsHtmlIDString[i]).val() );
	}
	return monthsVal;
}


function populateMonthDatePickers(pumMonths) {
	if (pumMonths) {
		for(var i = 0; i < monthsHtmlIDString.length; i++){
			for(var j = 0; j < pumMonths.length; j++){
				if (pumMonths[j].monthName && monthsHtmlIDString[i].indexOf(pumMonths[j].monthName.toLowerCase()) !== -1) {
					$(monthsHtmlIDString[i]).val(pumMonths[j].weekEnd);
				}
			}		
		}
	}
}

function populateEmptyDatePickers() {
	for(var i = 0; i < monthsHtmlIDString.length; i++){
		$(monthsHtmlIDString[i]).val("");
	}
}

function loadMonthsDatePickers() {
	var yearId = $('#yearList').find(":selected").val();
	var pumMonths;
	var getPUMMonthUrl = $('#myForm').attr('name');

	if (yearId) {
		getPUMMonthUrl = getPUMMonthUrl + '/' + yearId;
		$.ajax({
			url: getPUMMonthUrl,
			type: 'GET',
			traditional: true,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function (result) {
				pumMonths = result;
				if (pumMonths.length > 0) {
					SAVE_STATUS = "UPDATE";
					populateMonthDatePickers(pumMonths);
				} else {
					populateEmptyDatePickers();
				}
			},
			error: function (xhr, ajaxOptions, thrownError) {
				pumMonths = undefined;
				SAVE_STATUS = "SAVE";
				populateMonthDatePickers(pumMonths);
			}
		}); 
	}
}
