<%@page import="com.lec.service.InvalidPasswordException"%>
<%@page import="com.lec.service.DeleteMessageService"%>
<%@page import="com.lec.dao.MessageDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	String pw = request.getParameter("password");
	boolean invalidPassword = false;
	
	try {
		DeleteMessageService instance = DeleteMessageService.getInstance();
		instance.deleteMessage(id, pw);
	} catch(InvalidPasswordException e) {
		invalidPassword = true;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>방명록 메시지 삭제하기</h1>
	<%
		if(!invalidPassword) {
	%>
			<h3 style="color: blue;">방명록메시지가 성공적으로 삭제 되었습니다!!</h3>
	<%
		} else {
	%>
			<h3 style="color: red;">비밀번호가 일치 하지 않습니다. 다시 입력하세요!!</h3>
	<%
		}
	%>
	<br />
	<a href="listMessage.jsp">[목록으로 돌아가기]</a>
</body>
</html>










