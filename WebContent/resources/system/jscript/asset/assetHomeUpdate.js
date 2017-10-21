function confirmDelete() 
		{
			var retVal = confirm("Do you want to continue ?");
		   if( retVal == true ){
		      alert("Successfully Deleted");
			  return true;
		   }else{
			  return false;
		   }
		}


function AllowEdit()
{
	document.getElementById("recordedDateId").disabled=false;
	document.getElementById("assetNameId").disabled=false;
	document.getElementById("purchaseDateId").disabled=false;
	document.getElementById("serialNoId").disabled=false;
	document.getElementById("statusId").disabled=false;
	document.getElementById("asCodeId").disabled=false;
	document.getElementById("modelId").disabled=false;
	document.getElementById("hdiskId").disabled=false;
	document.getElementById("versionId").disabled=false;
	document.getElementById("ramId").disabled=false;
	
	document.getElementById("empNoId").disabled=false;
	document.getElementById("relRefId").disabled=false;
	document.getElementById("retRefId").disabled=false;
	
	document.getElementById("btnSaveId").disabled = false;
	
	
}