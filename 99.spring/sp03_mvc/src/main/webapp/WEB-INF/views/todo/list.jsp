<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Todo Lists</h1>
	<%-- ${ todoLists } --%>
	<ul>
	    <c:forEach var="todoDTO" items="${todoLists}">
	        <li>${todoDTO}</li>
	    </c:forEach>
	</ul>
</body>
</html>