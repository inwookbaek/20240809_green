<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		int number = 100;
	%>
	<%@include file="./jsp0605_includee.jspf" %>
	<hr />
	
	includee.jspf에서 가져온 변수 값 = <%= includee_value %>
</body>
</html>