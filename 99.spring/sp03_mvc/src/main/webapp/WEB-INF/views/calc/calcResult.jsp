<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${ param.num1 } + ${ param.num2 } = 
	${ Integer.parseInt(param.num1) + Integer.parseInt(param.num2) }</h1>
</body>
</html>