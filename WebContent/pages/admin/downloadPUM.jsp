<div class="ibm-container-body">
	<div class="ibm-container ibm-show-hide">
		<h2>Download PUM</h2>

	</div>
</div>
<form action="http://localhost:8080/onlinePUM/webapi/opum/downloadUtilization/${year}" method="get"  style="padding-left:10px" enctype="application/x-www-form-urlencoded">
	<label>Input PUM Year to download:</label>
	<input id="year" name="year"/>
	<br>
	<button>Download PUM File</button>
</form>
<script>
/* function getYear(){
	alert(document.getElementById('year').value);
} */
var start= 2000;
var min = new Date().getFullYear(),
max = min + 9,
select = document.getElementById('year');

for (var i = start; i<=max; i++){
var opt = document.createElement('option');
opt.value = i;
opt.innerHTML = i;
select.appendChild(opt);
}

</script>
<!-- "http://localhost:8080/onlinePUM/webapi/opum/downloadUtilization" -->