<%@page import="com.lec.model.MessageDTO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.lec.jdbc.ConnectionProvider"%>
<%@page import="com.lec.dao.MessageDAO"%>
<%@page import="com.lec.service.UpdateMessageService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MessageDAO messageDao = MessageDAO.getInstance();
	Connection conn = ConnectionProvider.getConnection();
	int id = Integer.parseInt(request.getParameter("id"));
	MessageDTO message = messageDao.select(conn, id);
	
	String guest_name = message.getGuest_name();
	String msg = message.getMessage();
	String pw = message.getPassword();
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>방명록 메시지 수정하기</h1>
	
	<form action="updateConfirm.jsp" method="post">
		<table border="1">
			<tr>
				<td>글번호</td>
				<td><input type="text" value=" <%= id %>" disabled /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" value=" <%= guest_name %>" disabled /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><textarea rows="5" cols="60" name="message"><%= msg %></textarea></td>
			</tr>		
		</table>	
	
		<input type="hidden" name="id" value="${ param.id }"/>
		메시지를 수정하려면 비밀번호를 입력하세요!!!<br>
		비밀번호 : <input type="password" name="password" placeholder="비밀번호를 입력하세요!!"/><br>
		<input type="submit" value="수정확인"/>
	</form>	
	
</body>
</html>