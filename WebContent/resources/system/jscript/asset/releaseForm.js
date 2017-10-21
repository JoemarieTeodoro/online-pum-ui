	
	function addCheck(tableID)
	{
		var asname, serial;
		asname = document.getElementById("assetNameId").value;
		serial = document.getElementById("serialNoId").value;
		
		if (/^[0-9A-Za-z-]*$/.test(serial) == true)
		{
			if(asname != "" && serial != "")
			{
				addRow(tableID);
				document.getElementById("assetNameId").value = null;
				document.getElementById("serialNoId").value = null;
			}

		}else{
			alert("INALID INPUT");
		}
		
		
		
		
	}
	
	function addRow(tableID) 
	{
		
	        var table = document.getElementById(tableID);
	        var rowCount = table.rows.length;
	        var row = table.insertRow(rowCount);
	        var counts = rowCount - 1;
	
	        var cell1 = row.insertCell(0);
	        var chk = document.createElement("input");
	        chk.type = "checkbox";
	        chk.name = "asdObj[" + counts + "].chk";
	        cell1.appendChild(chk);
	        
	        var cell1 = row.insertCell(1);
	        var assetName = document.createElement("input");
	        assetName.type = "text";
	        assetName.size = "35";
	        assetName.name = "asdObj[" + counts + "].assetName";
	        assetName.value = document.getElementById("assetNameId").value;
	        assetName.readOnly = true;
	        cell1.appendChild(assetName);
	
	       
	        var cell2 = row.insertCell(2);
	        var serialNo = document.createElement("input");
	        serialNo.type = "text";
	        serialNo.size = "35";
	        serialNo.name = "asdObj[" + counts + "].serialNo";
	        serialNo.value = document.getElementById("serialNoId").value;
	        serialNo.readOnly = true;
	        cell2.appendChild(serialNo);
	        
	        document.getElementById('reqAssetList').value = document.getElementById('reqAssetList').value + document.getElementById("serialNoId").value +",";  
	}
	
	
	function deleteRow(tableID) {
        try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;

        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked) {
                if(rowCount <= 1) {
                    alert("Cannot delete all the rows.");
                    break;
                }
                table.deleteRow(i);
                rowCount--;
                i--;
            }


        }
        }catch(e) {
            alert(e);
        }
    }
    
	
	

	function itemFieldTrap()
	{
		var asname, serial;
		serial = document.getElementById("assetNameId").value;

		switch (serial) 
		{
		case "AC Adapter": 
			document.getElementById("asCodeId").value = "ICA00ACAD"; 
			return true; break;
		
		case "Battery": 
			document.getElementById("asCodeId").value = "ICA00BATT";
			return true; break;
		
		case "Bag": 
			document.getElementById("asCodeId").value = "ICA00BAG";
			return true; break;
		
		case "Broad Band": 
			document.getElementById("asCodeId").value = "ICA00BROA";
			return true; break;
		
		case "CD/DVD Drive (Desktop)": 
			document.getElementById("asCodeId").value = "ICA00DCDD"; 
			return true; break;
		
		case "Hard Disk (Desktop)": 
			document.getElementById("asCodeId").value = "ICA00DHAR"; 
			return true; break;
		
		case "RAM (Desktop)": 
			document.getElementById("asCodeId").value = "ICA00DRAM"; 
			return true; break;
		
		case "Kensington":
			document.getElementById("asCodeId").value = "ICA00KENS";
			return true; break;
		
		case "Keyboard": 
			document.getElementById("asCodeId").value = "ICA00KEYB"; 
			return true; break;
		
		case "Laptop": 
			document.getElementById("asCodeId").value = "ICA00LAPT"; 
			return true; break;
		
		case "CD/DVD Drive (Laptop)": 
			document.getElementById("asCodeId").value = "ICA00LCDD"; 
			return true; break;
			
		case "Hard Disk (Laptop)": 
			document.getElementById("asCodeId").value = "ICA00LHAR";
			return true; break;
			
		case "RAM (Laptop)": 
			document.getElementById("asCodeId").value = "ICA00LRAM";
			return true; break;
			
		case "Mobile Phone": 
			document.getElementById("asCodeId").value = "ICA00MOBI";
			return true; break;	
		
		case "Monitor":  
			document.getElementById("asCodeId").value = "ICA00MONI"; 
			return true; break;
		
		case "Mouse": 
			document.getElementById("asCodeId").value = "ICA00MOUS"; 
			return true; break;
			
		case "Printer": 
			document.getElementById("asCodeId").value = "ICA00PRIN";
			return true; break;
				
		case "Software": 
			document.getElementById("asCodeId").value = "ICA00SOFT";
			return true; break;	
			
		case "Telephone": 
			document.getElementById("asCodeId").value = "ICA00TELE"; 
			return true; break;	
	
		}
		return true;
	}
	
	
	
