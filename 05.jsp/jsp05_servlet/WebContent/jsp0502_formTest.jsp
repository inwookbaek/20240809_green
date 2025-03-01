<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="test" method="get">
		<input type="text" name="name" value="홍길동">
		<input type="submit" value="doGet()"/>
	</form>
	
	<form action="test" method="post">
		<input type="text" name="name" value="홍길동">
		<input type="submit" value="doPost()"/>
	</form>
</body>
</html>