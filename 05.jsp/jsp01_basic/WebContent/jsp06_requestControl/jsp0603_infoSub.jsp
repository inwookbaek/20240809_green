<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String type = request.getParameter("type");
	if(type == null) return;
%>
<hr />
<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>제품타입</td>
		<td><b><%= type %></b></td>
	</tr>
	<tr>
		<td>제품등급</td>
		<td>
			<%
				if(type.equalsIgnoreCase("A")) {
			%>
					<strong>제품상태가 A급입니다!!!</strong>
			<%
				} else {
			%>
					<strong>제품상태가 B급입니다!!!</strong>					
			<%
				}
			%>
		</td>
	</tr>
</table>