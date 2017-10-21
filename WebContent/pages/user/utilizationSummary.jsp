<%-- <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
			<div class="ibm-title">
				<h2>Utilization Summary</h2>
				<hr>
			</div> --%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 style="padding-top: 5px; margin-top: 8px; margin-bottom: 5px;">Utilization
	Summary</h2>
<div class="ibm-container" style="width: 650px">
	<div class="ibm-container-body">
		<ul class="ibm-twisty">
			<s:iterator value="yearCalculation.quarters" var="quarter">
				<li><a class="ibm-twisty-trigger" href="#toggle"><img
						alt="-" src="//www.ibm.com/i/c.gif" /></a> <span
					class="ibm-twisty-head"><strong><s:property
								value="#quarter.name" /></strong></span>
					<div class="ibm-twisty-body">
						<ul class="ibm-twisty">
							<table cellspacing="0" cellpadding="0" border="0"
								class="ibm-data-table ibm-sortable-table ibm-alternating-col">
								<thead>
									<tr>
										<th scope="col"><center>Total Available Hours:</center></th>
										<th scope="col"><center>Total Actual Hours:</center></th>
										<th scope="col"><center>QTD:</center></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><center>
												<s:property value="#quarter.totalHours" />
											</center></td>
										<td><center>
												<s:property value="#quarter.numberOfAvailableHours" />
											</center></td>
										<td><center>
												<s:property value="#quarter.quarterToDateUtilization" />
												%
											</center></td>
									</tr>
								</tbody>
							</table>


							<s:iterator value="#quarter.months" var="month">
								<table cellspacing="0" cellpadding="0" border="0"
									class="ibm-data-table ibm-sortable-table ibm-alternating-col">
									<thead>
										<tr>
											<th scope="col" style="width: 100px"><center>Month:</center></th>
											<th scope="col" style="width: 195px"><center>Week 1 Hours:</center></th>
											<th scope="col" style="width: 195px"><center>Week 2 Hours:</center></th>
											<th scope="col" style="width: 195px"><center>Week 3 Hours:</center></th>
											<th scope="col" style="width: 195px"><center>Week 4 Hours:</center></th>
											<s:if test="#month.weeks.size == 5">
											<th scope="col" style="width: 195px"><center>Week 5 Hours:</center></th>
											</s:if> 
											<th scope="col" style="width: 195px"><center>Total VL:</center></th>
											<th scope="col" style="width: 195px"><center>Total SL:</center></th>
											<th scope="col" style="width: 195px"><center>Total OL:</center></th>
											<th scope="col" style="width: 195px"><center>Total Available Hours:</center></th>
											<th scope="col" style="width: 195px"><center>Total Actual Hours:</center></th>
											<th scope="col" style="width: 195px"><center>MTD:</center></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><center>
													<s:property value="#month.name" />
												</center></td>

											<s:iterator value="#month.weeks" var="week">
												<td><center>
														<s:property value="#week.totalHours" />
													</center></td>
											</s:iterator>

											<td><center>
													<s:property value="#month.numberOfVL" />
												</center></td>
											<td><center>
													<s:property value="#month.numberOfSL" />
												</center></td>
											<td><center>
													<s:property value="#month.numberOfOL" />
												</center></td>
											<td><center>
													<s:property value="#month.numberOfAvailableHours" />
												</center></td>
											<td><center>
													<s:property value="#month.totalHours" />
												</center></td>
											<td><center>
													<s:property value="#month.monthToDateUtilization" />
													%
												</center></td>
										</tr>
									</tbody>
								</table>
							</s:iterator>

						</ul>
					</div></li>
			</s:iterator>
		</ul>
	</div>
</div>


<ul class="ibm-twisty">
	<li><a class="ibm-twisty-trigger" href="#toggle"><img alt="-"
			src="//www.ibm.com/i/c.gif" /></a> <span class="ibm-twisty-head"><strong>YTD
				Calculations</strong></span>
		<div class="ibm-twisty-body">
			<ul class="ibm-twisty">
				<table cellspacing="0" cellpadding="0" border="0"
					class="ibm-data-table ibm-sortable-table ibm-alternating-col">
					<thead>
						<tr>
							<th scope="col" style="width: 100px"><center>VL YTD:</center></th>
							<th scope="col" style="width: 100px"><center>SL YTD:</center></th>
							<th scope="col" style="width: 100px"><center>EL YTD:</center></th>
							<th scope="col" style="width: 100px"><center>OL YTD:</center></th>
							<th scope="col" style="width: 100px"><center>TR YTD:</center></th>
							<th scope="col" style="width: 100px"><center>HO YTD:</center></th>
							<th scope="col" style="width: 100px"><center>CDO YTD:</center></th>
							<th scope="col" style="width: 100px"><center>Total Hours YTD:</center></th>
							<th scope="col" style="width: 100px"><center>Available Hours YTD:</center></th>
							<th scope="col" style="width: 100px"><center>YTD:</center></th>
					</thead>
					<tbody>
						<tr>

							<td><center><s:property value="yearCalculation.numberOfVL" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfSL" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfEL" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfOL" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfTR" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfHO" /></center></td>
							<td><center><s:property value="yearCalculation.numberOfCDO" /></center></td>
							<td><center><s:property value="yearCalculation.totalHours" /></center></td>
							<td><center><s:property
									value="yearCalculation.numberOfAvailableHours" /></center></td>
							<td><center><s:property
									value="yearCalculation.yearToDateUtilization" />%</center></td>
						</tr>
					</tbody>
				</table>
			</ul>
		</div></li>
</ul>