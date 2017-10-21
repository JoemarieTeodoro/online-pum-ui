	
	function exeEntry(){
        if(document.getElementById("newEntryId").checked)
        {
        	ClearField();
    		document.getElementById("assetNameId").value = "";	
    		document.getElementById("asCodeId").value = "";	    		
			document.getElementById("assetNameId").disabled = true;
			document.getElementById("asCodeId").disabled = true;
			document.getElementById("newAssetId").disabled = false;
			document.getElementById("newCodeId").disabled = false;
			
			document.getElementById("versionId").disabled = false;
			document.getElementById("ramInput_Id").disabled = false;
			document.getElementById("ramUnitInput_Id").disabled = false;
			document.getElementById("hDiskInput_Id").disabled = false;
			document.getElementById("hDiskUnitInput_Id").disabled = false;

        }
        
        if(document.getElementById("newEntryId").checked == false)
        {
        	ClearField();
    		document.getElementById("assetNameId").value = "";	
    		document.getElementById("asCodeId").value = "";	
			document.getElementById("assetNameId").disabled = false;
			document.getElementById("asCodeId").disabled = false;
			document.getElementById("newAssetId").disabled = true;
			document.getElementById("newCodeId").disabled = true;
			
			document.getElementById("versionId").disabled = true;
			document.getElementById("ramInput_Id").disabled = true;
			document.getElementById("ramUnitInput_Id").disabled = true;
			document.getElementById("hDiskInput_Id").disabled = true;
			document.getElementById("hDiskUnitInput_Id").disabled = true;
        }
	}

	function itemFieldTrap()
	{	
		var asname, serial;
		serial = document.getElementById("assetNameId").value;

		if(serial == "RAM (Desktop)" || serial == "RAM (Laptop)"){
			ClearField(); DefaultHide();
			document.getElementById("ramInput_Id").disabled = false;
			document.getElementById("ramUnitInput_Id").disabled = false;
		}
		else if(serial == "Hard Disk (Desktop)" || serial == "Hard Disk (Laptop)"){
			ClearField(); DefaultHide();
			document.getElementById("hDiskInput_Id").disabled = false;
			document.getElementById("hDiskUnitInput_Id").disabled = false;
		}
		else{
			ClearField(); DefaultHide();
			}
		
		return true;
		
		
	}
	
	function DefaultHide()
	{
		document.getElementById("versionId").disabled = true;
		document.getElementById("ramInput_Id").disabled = true;
		document.getElementById("ramUnitInput_Id").disabled = true;
		document.getElementById("hDiskInput_Id").disabled = true;
		document.getElementById("hDiskUnitInput_Id").disabled = true;
		document.getElementById("newAssetId").disabled = true;
		document.getElementById("newCodeId").disabled = true;
		document.getElementById("assetNameId").disabled = false;
		document.getElementById("asCodeId").disabled = false;
	}
	
	function ClearField()
	{
		document.getElementById("serialNoId").value ="";
		document.getElementById("modelId").value ="";
		document.getElementById("purchaseDateId").value ="";
		document.getElementById("statusId").value ="";
		document.getElementById("versionId").value ="";
		document.getElementById("ramInput_Id").value ="";
		document.getElementById("ramUnitInput_Id").value ="";
		document.getElementById("hDiskInput_Id").value ="";
		document.getElementById("hDiskUnitInput_Id").value ="";
		document.getElementById("newAssetId").value ="";
		document.getElementById("newCodeId").value ="";
	}
	
	function assignRAMValue()
	{ 
		if(document.getElementById("newEntryId").checked){
			document.getElementById("chkOptionId").value = "true";
		}else{
			document.getElementById("chkOptionId").value = "false";
		}
		
		if(document.getElementById("ramInput_Id").value != null &&
				document.getElementById("ramUnitInput_Id").value != ""){
			
			document.getElementById("ramSizeId").value =
				document.getElementById("ramInput_Id").value +" "+
				document.getElementById("ramUnitInput_Id").value;
		}
		if(document.getElementById("hDiskInput_Id").value != null &&
				document.getElementById("hDiskUnitInput_Id").value != ""){
			
			document.getElementById("hDiskSizeId").value =
				document.getElementById("hDiskInput_Id").value +" "+
				document.getElementById("hDiskUnitInput_Id").value;
			
		}
	}
	
