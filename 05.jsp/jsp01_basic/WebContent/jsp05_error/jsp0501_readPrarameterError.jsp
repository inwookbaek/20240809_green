<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>request파라미터 확인하기</h1>
	<% 
		try {
	%>
			<%= request.getParameter("id").toUpperCase() %>			
	<%
		} catch(Exception e) {
	%>
			<b style="color: red;">id 파라미터가 전달되지 않았습니다!!</b>
	<%
			e.getMessage();
		}
	%>

</body>
</html>