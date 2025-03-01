<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>c:url</h1>
	
	<!-- https://search.daum.net/search?q=%EA%B3%B5%EC%9B%90&w=blog -->
	
	<c:url var="searchUrl" value="http://search.daum.net/search" >
		<c:param name="w" value="blog"></c:param>
		<c:param name="q" value="공원"/>
	</c:url>
	
	<ul>
		<li>${ searchUrl }</li>
		<li><c:url value="jsp1102_if.jsp"/></li>
		<li><c:url value="./jsp1102_if.jsp"/></li>
	</ul>
</body>
</html>