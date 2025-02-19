<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>JSP방식</h1>
	<% 
		if(true) {
	%>
			<h3>참입니다!!!!</h3>
	<%
		}
	%>
	
	<h1>JSTL 방식</h1>
	<c:if test="true">
		<h3>참입니다!!!</h3>
	</c:if>
	<!-- 실습. id와 age를 전달받아서 
		 id가 hong이면 "홍길동"출력 
	     age 18이상이면 "성인입니다!"
	     EL과 JSTL을 이용해서 출력하기  
	     //~~.jsp?id=hong&age=20     
	-->
	아이디 : <%= request.getParameter("id") %> = ${ param.id }<br>
	나이   : <%= request.getParameter("age") %> = ${ param.age }<br>
	
	<hr />
	<c:if test="${ param.id == 'hong' }">
		아이디는 ${ param.id }이고 회원이름은 홍길동입니다!!
	</c:if>
	<br />
	<c:if test="${ param.age > 18 }">
		${ param.age }나이는 성인입니다!!
	</c:if>
</body>
</html>














