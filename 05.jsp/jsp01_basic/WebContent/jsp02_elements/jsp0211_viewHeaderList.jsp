<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>request Header 정보</h1>
	<%
		Enumeration headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String headerName = (String) headers.nextElement();
			String headerValue = request.getHeader(headerName);
	%>			
			<%= headerName %> = <%= headerValue %><br>
	<% 
		}
	%>
</body>
</html>