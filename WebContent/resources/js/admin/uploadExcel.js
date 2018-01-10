function isValid(form){
	with(form){
		return validateFileExtension(file, ["xlsx"]);
	}
}

$("#upload-btn").click(function(e){
	e.preventDefault();
	var form = $("#file")[0];
	var data = new FormData(form);
	var link = $(form).attr("action");
	if(!isValid(form)){
		alert("Only excel (.xlsx) files are allowed.");
		return
	}
	$.ajax({
		type : "POST",
		url : link,
		data : data,
		contentType : false,
		dataType : false,
		success : function(data){
			alert(data);
			window.location.reload();
		},
		error : function(data, textStatus, xhr){
			alert(data.responseText);
			window.location.reload();
		},
		processData : false
	});
});