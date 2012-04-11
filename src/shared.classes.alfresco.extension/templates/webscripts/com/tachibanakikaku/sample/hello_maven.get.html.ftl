${title}
<table>
	<th>index</th><th>noderef</th>
	<#list nodes as node>
	<tr>
		<td>${node_index}</td><td>${node}</td>
	</tr>
	</#list>
</table>