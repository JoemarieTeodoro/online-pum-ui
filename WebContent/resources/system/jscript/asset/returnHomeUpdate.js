function confirmDelete() 
	{
		var retVal = confirm("Do you want to continue ?");
	   if( retVal == true ){
	      alert("Successfully Deleted!");
		  return true;
	   }else{
		  return false;
	   }
	}
   


function AllowEdit()
{
	document.getElementById("empNoId").disabled=false;
	document.getElementById("reqTypeId").disabled=false;
	document.getElementById("reqDateId").disabled=false;
	document.getElementById("reqNoId").disabled=false;
	document.getElementById("reqNameId").disabled=false;
	document.getElementById("reqEmailId").disabled=false;
	
	document.getElementById("retById").disabled=false;
	document.getElementById("asCustoId").disabled=false;
	document.getElementById("onsiteId").disabled=false;
	document.getElementById("retDateId").disabled=false;
	
	document.getElementById("btnSaveId").disabled=false;
	document.getElementById("accessId").disabled=false;
	document.getElementById("wstId").disabled=false;
	document.getElementById("eamtId").disabled=false;
	document.getElementById("uninstId").disabled=false;
}

function enableComplianceOpt(){
	
	var reqType;
	
	reqType = document.getElementById("reqTypeId").value;
	
	if(reqType == "Resign Employee")
	{
		clearCheckBox();
		document.getElementById("accessId").disabled=false;
		document.getElementById("wstId").disabled=false;
		document.getElementById("eamtId").disabled=false;
		document.getElementById("uninstId").disabled=false;
		return true;
		
	}
	else{
		clearCheckBox();
		document.getElementById("accessId").disabled=true;
		document.getElementById("wstId").disabled=true;
		document.getElementById("eamtId").disabled=true;
		document.getElementById("uninstId").disabled=true;
		return true;
	}
	
	return true;
}


	function clearCheckBox(){
		document.getElementById("accessId").checked = false;
		document.getElementById("wstId").checked = false;
		document.getElementById("eamtId").checked = false;
		document.getElementById("uninstId").checked = false;
	}