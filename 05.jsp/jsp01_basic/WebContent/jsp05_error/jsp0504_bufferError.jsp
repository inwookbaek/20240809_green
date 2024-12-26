<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page buffer="1kb" autoFlush="false"%>
<%@page errorPage="/jsp05_error/error/viewErrorMessage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Buffer 플러쉬이후 에러발생 처리하기</h1>
	<% for(int i=0;i<=3;i++) { out.println("no = " + i + "<br>"); } %>
	<%= 10/0 %>
</body>
</html>