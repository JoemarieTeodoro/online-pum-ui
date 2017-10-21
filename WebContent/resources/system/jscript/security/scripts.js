function getFirstDay(theYear, theMonth){
    var firstDate = new Date(theYear,theMonth,1);
    return firstDate.getDay();
}
function getMonthLen(theYear, theMonth) {
    var oneDay = 1000 * 60 * 60 * 24;
    var thisMonth = new Date(theYear, theMonth, 1);
    var nextMonth = new Date(theYear, theMonth + 1, 1);
    var len = Math.ceil((nextMonth.getTime() - thisMonth.getTime())/oneDay);
    return len;
}
function getY2KYear(today) {
    var yr = today.getYear();
    return ((yr < 1900) ? yr+1900 : yr);
}
theMonths = new MakeArray(12);
function MakeArray(n) {
    this[0] = "January";
    this[1] = "February";
    this[2] = "March";
    this[3] = "April";
    this[4] = "May";
    this[5] = "June";
    this[6] = "July";
    this[7] = "August";
    this[8] = "September";
    this[9] = "October";
    this[10] = "November";
    this[11] = "December";
    this.length = n;
    return this;
}
function next(form) {

	
	var month = form.chooseMonth.selectedIndex;
	var year = form.chooseYear.selectedIndex;
	if(month==11)
	{
		if(year==14)
			form.chooseYear.selectedIndex=0;
		else
			form.chooseYear.selectedIndex=year+1;
		form.chooseMonth.selectedIndex=0;
		form.asd.value=year+1;
	}
	else
		form.chooseMonth.selectedIndex=month+1;
	//form.asd.value=form.chooseMonth.selectedIndex+":"+form.chooseYear.selectedIndex;
	populateFields(form);
}

function prev(form) {
	var month = form.chooseMonth.selectedIndex;
	var year = form.chooseYear.selectedIndex;
	
	if(month==0)
	{
		if(year==0)
			form.chooseYear.selectedIndex=14;
		else
			form.chooseYear.selectedIndex=year-1;
		form.chooseMonth.selectedIndex=11;
		form.asd.value=year-1;
	}
	else
		form.chooseMonth.selectedIndex=month-1;
	//form.asd.value=form.chooseMonth.selectedIndex+":"+form.chooseYear.selectedIndex;
	populateFields(form);
}

function populateFields(form) {
    var theMonth = form.chooseMonth.selectedIndex;
    var theYear = form.chooseYear.options[form.chooseYear.selectedIndex].text;
    var firstDay = getFirstDay(theYear, theMonth);
    // total number of <TD>...</TD> tags needed in for loop below
    var howMany = getMonthLen(theYear, theMonth);
    // fill fields of table
    for (var i = 0; i < 42; i++) {
        if (i < firstDay || i >= (howMany + firstDay)) 
        {
            form.oneDay[i].value = "";
            form.oneDay[i].disabled="disabled";
        } else {
            form.oneDay[i].value = i - firstDay + 1;
            form.oneDay[i].disabled="";
       }
    }
    document.getElementById('hiddenTable').style.display='';
}

function getOther(sel,fld){
	fld.style.display = (sel.selectedIndex==sel.options.length-1)?"inline":"none";
	fld.required =(sel.selectedIndex==sel.options.length-1)?true:false;
}

function click(name,id)
{
	document.getElementById("tempTable").hidden = "hidden"; 
	document.getElementById("form").hidden = ""; 
	document.getElementById("txtContactPerson").value =name; 
	document.getElementById("EmpId").value =id; 
	return false;
}
function getOther(sel,fld){
	fld.style.display = (sel.selectedIndex==sel.options.length-1)?"inline":"none";
	fld.required =(sel.selectedIndex==sel.options.length-1)?true:false;
}

function cancel() {
	document.getElementById("IdNo").required="";
	document.getElementById("Name").required="";
	document.getElementById("Company").required="";
	document.getElementById("ContactPerson").required="";
	document.getElementById("IdPresented").required="";
	document.getElementById("hiddenOther").required="";
}
