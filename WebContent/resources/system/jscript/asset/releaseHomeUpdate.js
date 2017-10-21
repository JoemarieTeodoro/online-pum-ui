

	function confirmDelete() 
	{
		var retVal = confirm("Do you want to continue ?");
	   if( retVal == true ){
	      alert("Succesfully Deleted!");
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
		document.getElementById("relToId").disabled=false;
		document.getElementById("relById").disabled=false;
		document.getElementById("relDateId").disabled=false;
		
		document.getElementById("btnSaveId").disabled = false;
	}