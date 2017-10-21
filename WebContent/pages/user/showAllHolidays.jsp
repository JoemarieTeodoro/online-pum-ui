<%@ taglib prefix="s" uri="/struts-tags"%>
<s:head/>
<br>
<body>
		<div>
			<table summary="Data table with alternating rows example" 
				class="ibm-data-table ibm-sortable-table ibm-alternating-col">
				<caption>
				<em>Holidays</em>
				</caption>


				<thead>

					<tr>
						<th scope="col" class="ibm-sort" width="100px"><a href="#sort"><span>Name</span><span class="ibm-icon">&nbsp;</span></a></th>
						<th scope="col" class="ibm-sort"><a href="#sort"><span>Date</span><span class="ibm-icon">&nbsp;</span></a></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="holidayList.holidayList" var="holiday">
					<tr>
						<td><s:property value="name"/></td>
						<td><s:property value="date"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
</body>