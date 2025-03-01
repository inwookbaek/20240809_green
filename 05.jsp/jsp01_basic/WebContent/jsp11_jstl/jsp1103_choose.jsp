<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- ?id=hong&age=22 -->
	<!-- if~else~  -->
	<h1>jstl - choose 사용하기</h1>
	<ul>
		<c:choose>
			<c:when test="${ param.id == 'hong' }">
				<li>당신의 id는 ${ param.id }이고 이름은 홍길동입니다!</li>
			</c:when>
			<c:when test="${ param.age > 18 }">
				<li>${ param.age }나이는 성인입니다!!</li>
			</c:when>
			<c:otherwise>
				<li>당신의 id는 hong아니고 18세이상이 아닙니다!!!</li>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>