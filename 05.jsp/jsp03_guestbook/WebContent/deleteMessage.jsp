<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>방명록 메시지 삭제 확인</h1>
	<form action="deleteConfirm.jsp" method="post">
		<input type="hidden" name="id" value="${ param.id }"/>
		메시지를 삭제하려면 비밀번호를 입력하세요!!!<br>
		비밀번호 : <input type="password" name="password" placeholder="비밀번호를 입력하세요!!"/><br>
		<input type="submit" value="삭제확인"/>
	</form>
	
	
</body>
</html>