<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>timeZone tag</h1>
	<c:set var="now" value="<%= new java.util.Date() %>"/>
	
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	<fmt:timeZone value="Pacific/Pitcairn">
		<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	</fmt:timeZone>
	
	<h4>TimeZone</h4>
	<c:forEach var="tz" items="<%= java.util.TimeZone.getAvailableIDs() %>">
		${tz}<br>
	</c:forEach>
</body>
</html>