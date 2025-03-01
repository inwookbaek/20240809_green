<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String forwardPage = "";
	String code = request.getParameter("code");
	
	if(code.equals("A")) {
		forwardPage = "./viewpage/a.jsp";
	} else if(code.equals("B")) {
		forwardPage = "./viewpage/b.jsp";		
	} else if(code.equals("C")) {
		forwardPage = "./viewpage/c.jsp";
	}
%>
<h1>jsp0607_view.jsp</h1>

<jsp:forward page="<%= forwardPage %>" />