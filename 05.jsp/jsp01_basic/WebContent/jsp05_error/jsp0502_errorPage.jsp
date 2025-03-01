<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page errorPage="/jsp05_error/error/viewErrorMessage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- 에러가 발생했을 때 사전에 정의된 에러페이지로 
	     포워딩처리를 할 수가 있다. 포워딩처리를 하기 
	     위해서는 page디렉티브에 errorPage속성에 포워딩
	     할 페이지주소를 설정해야 한다.
	-->
	회원아이디 : <%= request.getParameter("id").toUpperCase() %>
</body>
</html>