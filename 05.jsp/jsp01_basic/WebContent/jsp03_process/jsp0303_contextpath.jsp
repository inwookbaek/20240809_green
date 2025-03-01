<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h3>Context Path = <%= request.getContextPath() %></h3>
	<h3>Context Real Path = <%= request.getServletContext().getRealPath(request.getContextPath()) %></h3>
</body>
</html>