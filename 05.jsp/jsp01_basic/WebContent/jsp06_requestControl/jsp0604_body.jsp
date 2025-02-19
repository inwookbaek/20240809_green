<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>include 전후 파라미터값 확인하기</h1>
	<!-- main과 sub에서 name이라는 파라미터가 존재할 경우  
		jsp0604_body?name=손흥민
	-->
	include전 name파라미터값 = <%= request.getParameter("name") %>
	<hr />
	
	<jsp:include page="jsp0604_bodySub.jsp" flush="false">
		<jsp:param value="홍길동" name="name"/>
	</jsp:include>
	<hr />
	
	include후 name파라미터값 = <%= request.getParameter("name") %>
</body>
</html>