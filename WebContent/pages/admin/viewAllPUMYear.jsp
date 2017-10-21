<%@ taglib prefix="s" uri="/struts-tags"%>
<s:head/>
<br>
<body>
		<div>
			<table summary="Data table with alternating rows example" 
				class="ibm-data-table ibm-sortable-table ibm-alternating-col">
				<caption>
				<em>Pum Year</em>
				</caption>
				<thead>
					<tr>
						<th scope="col" class="ibm-sort" width="100px"><a href="#sort"><span>PumYear</span><span class="ibm-icon"> </span></a></th>
						<th scope="col" class="ibm-sort" width="100px"><a href="#sort"><span>Start</span><span class="ibm-icon"> </span></a></th>
						<th scope="col" class="ibm-sort" width="100px"><a href="#sort"><span>End</span><span class="ibm-icon"> </span></a></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="pumYearList.pumYearList" var="pumyear">
					<tr>
							<td><s:property value="pumYear"/></td>
							<td><s:property value="start"/></td>
							<td><s:property value="end"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
</body> 