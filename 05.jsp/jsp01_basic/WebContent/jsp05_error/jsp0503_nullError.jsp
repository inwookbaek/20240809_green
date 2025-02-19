<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- web.xml에 NullPointerExecption 설정 
	  <error-page>
	  	<exception-type>java.lang.NullPointerException</exception-type>
	  	<location>/jsp05_error/error/nullPointerError.jsp</location>
	  </error-page>		
	-->
	회원아이디 : <%= request.getParameter("id").toUpperCase() %>
</body>
</html>