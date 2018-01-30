$("#form").submit(function(e) {
	e.preventDefault();
	var leaveName = $("#leaveName")["0"].value.toUpperCase();
	if (!isLeaveNameValid(leaveName)) {
		alert("Leave Name is not valid");
		return;
	}
	var link = $("#form").attr("action");
	$.ajax({
		method : "POST",
		url : link,
		data : createJSON(leaveName),
		datatype : "json",
		contentType : 'application/json',
		processData : false
	})
	.done(function(data) {
		alert("Past date successfully inserted.");
		$("#form").trigger("reset");
	})
	.error(function(data) {
		alert(data.responseText);
	});
});

function isLeaveNameValid(leaveName) {
	return leaveName === "VL" || leaveName === "OL" || leaveName === "CDO" || Number(leaveName);
}

function createJSON(leaveName) {
	return JSON.stringify({
		"employeeID": $("#employeeID")["0"].value,
		"date": $("#date")["0"].value,
		"status": "Approved",
		"leaveName": leaveName,
		"value": Number(leaveName) ? leaveName : 0
	});
}