<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>session정보 조회</h1>
	회원ID : <%= session.getAttribute("id") %><br>
	회원명 : <%= session.getAttribute("name") %>	
</body>
</html>