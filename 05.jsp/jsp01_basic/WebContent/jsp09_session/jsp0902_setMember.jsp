<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.setAttribute("id", "sohyang");
	session.setAttribute("name", "소향");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Session에 회원정보를 저장/조회 하기</h1>
	session에 로그인한 회원정보를 저장했습니다!!<br>
	
	회원ID : <%= session.getAttribute("id") %><br>
	회원명 : <%= session.getAttribute("name") %><br>
</body>
</html>