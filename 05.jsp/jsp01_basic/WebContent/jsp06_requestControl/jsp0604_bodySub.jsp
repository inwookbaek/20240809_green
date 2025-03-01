<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>jsp0604_bodySub.jsp</h3>
bodySub에서 name 파라미터 값 = <%= request.getParameter("name") %>
name 파라미터값목록 :
<ul>
	<%
		String[] names = request.getParameterValues("name");
		for(String name:names) {
	%>
			<li><%= name %></li>
	<%
		}
	%>
</ul>
