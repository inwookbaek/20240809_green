<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring MVC with Servlet</h1>
	spring boot 3.0이상에서 jstl을 사용할 경우 환경설정(bundle.gradle 의존주입)
	<ol>
		<li>implementation 'jakarta.servlet:jakarta.servlet-api'</li>
		<li>implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api</li>
		<li>implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'</li>
	</ol>
	<hr />
	
	<a href="input">계산기</a><br>
</body>
</html>