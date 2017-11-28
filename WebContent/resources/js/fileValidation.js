var validFileTypes = [ "csv" ];

function validation(thisform) {
	with (thisform) {
		return validateFileExtension(file, validFileTypes);
	}
}

function validateFileExtension(component, extns) {
	var flag = 0;
	with (component) {
		var ext = value.substring(value.lastIndexOf('.') + 1);
		for (i = 0; i < extns.length; i++) {
			if (ext == extns[i]) {
				flag = 0;
				break;
			} else {
				flag = 1;
			}
		}
		if (flag != 0) {
			component.value = "";
			component.style.backgroundColor = "#eab1b1";
			component.style.border = "thin solid #000000";
			component.focus();
			return false;
		} else {
			return true;
		}
	}
}