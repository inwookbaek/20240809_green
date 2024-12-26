<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원정보수정하기</h1>
	<form action="./jsp0102_updateMember.jsp" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" placeholder="아이디를 입력하세요..."/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" placeholder="비밀번호를 입력 하세요..."/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" placeholder="이름을 입력하세요..."/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="eMail" name="email" placeholder="eMail을 입력하세요..."/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="회원수정하기"/></td>
			</tr>		
		</table>
	</form>
</body>
</html>