<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.lec.el.UseStaticMethod" %>
<%
	request.setAttribute("price", 12345L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>EL에서 정적메서드 호출하기</h1>
	JSP의 request.getAttribute("price") = ${ price }<br>
	입력된 값은 <b>${ UseStaticMethod.numberFormat(price, '#,##0') }</b> 원 입니다!! 
</body>
</html>