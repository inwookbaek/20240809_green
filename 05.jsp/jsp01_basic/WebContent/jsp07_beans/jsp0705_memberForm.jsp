<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	http://localhost:8088/jsp01_basic/jsp07_beans/jsp0706_memberJoin.jsp?id=hong&name=%ED%99%8D%EA%B8%B8%EB%8F%99&email=hong%40gmail.com
	<hr />
	<form action="./jsp0706_memberJoin.jsp" method="post">
		<table border="1" cellpadding="0" celspacing="0">
			<tr>
				<td>아이디</td>
				<td colspan="3"><input type="text" name="id" size="10" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="10"></td>
				<td>이메일</td>
				<td><input type="email" name="email" size="20"></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="회원가입"/></td>
			</tr>
		</table>	
	</form>
</body>
</html>