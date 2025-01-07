<%@page import="com.lec.service.WriteMessageService"%>
<%@page import="com.lec.model.MessageDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("guest_name");
	
	MessageDTO message = new MessageDTO();
	message.setGuest_name(name);
	message.setPassword(request.getParameter("password"));
	message.setMessage(request.getParameter("message"));
	
	WriteMessageService writeService = WriteMessageService.getInstance();
	writeService.write(message);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>방명록등록 성공!!!</h1>
<%-- 	<%= message.toString() %><br>
	<%= message.getGuest_name() %><br>	
	<%= message.getPassword() %><br>	
	<%= message.getMessage() %><br>	 --%>
	방명록이 성공적으로 등록되었습니다!!<br>
	<a href="listMessage.jsp">[목록보기]</a>
</body>
</html>