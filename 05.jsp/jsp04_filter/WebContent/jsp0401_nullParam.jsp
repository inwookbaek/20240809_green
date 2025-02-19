<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Filter - NullParameterFilter</h1>
	id : <%= request.getParameter("id") %><br>
	name : <%= request.getParameter("name") %><br>
	member : <%= request.getParameter("member") %><br>
</body>
</html>