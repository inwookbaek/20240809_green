<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>HTML tag 삭제하기</h1>
	
	<c:set var="dateEL" value="<%= new java.util.Date() %>"/>
	
	<font size="10">현재시간은 ${dateEL}</font><br />
	
	<tf:removeHtml trim="true">
		<font size="10">현재시간은 ${dateEL}</font><br />	
	</tf:removeHtml>
	<br />

</body>
</html>