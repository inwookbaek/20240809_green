<%@page import="com.lec.cookie.Cookies"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.addCookie(Cookies.createCookie("id", "sonny"));
	response.addCookie(Cookies.createCookie("name", "손흥민"));
	response.addCookie(Cookies.createCookie("path", "경로", "/jsp08_cookie", -1));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Java로 Cookie 생성하기</h1>
	Java클래스로 Cookie를 생성했습니다!!!
</body>
</html>