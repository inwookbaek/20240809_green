<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		if((String) session.getAttribute("id") != null) {
	%>
			<h3>로그인이 되어있습니다!!!</h3>
	<%
		} else {
	%>
			<h3>로그인이 않되었습니다! 로그인을 해주세요!!</h3>
	<%
		}
	%>
</body>
</html>