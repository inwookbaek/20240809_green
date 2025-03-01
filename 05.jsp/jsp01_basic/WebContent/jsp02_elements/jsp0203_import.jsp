<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date, java.util.Calendar" %>
<%-- <%@page import="java.util.Calendar" %> --%>
<!--  
	import속성
	
	자바에서 자바클래스의 Full Name대신 단순 이름을 사용하기 위해 import구문을 사용하는 
	것 처럼 JSP는 디렉티브이 import속성을 사용해서 JSP코드에서 클래스의 단수이름을 사용
	할 수 있다. import속성에는 아래와 같이 여러개의 값을 동시에 지정할 수 있다.
	%@ page import="java.util.Calendar, java.util.Date" %>
	 
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- import속성을 사용하지 않는 경우에는 Full Name을 지정 -->
	현재시간 : <%= new java.util.Date() %><br>
	현재시간 : <%= new Date() %><br>
	<hr />
	
	<% Calendar cal = Calendar.getInstance(); %>
	오늘은 
		<%= cal.get(Calendar.YEAR) %>년
		<%= cal.get(Calendar.MONTH) + 1%>월
		<%= cal.get(Calendar.DATE)%>일
	입니다!!!!
	
</body>
</html>


















