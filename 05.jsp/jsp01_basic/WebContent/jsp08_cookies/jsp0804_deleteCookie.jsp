<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies !=null && cookies.length > 0) {
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("addr")) {
				Cookie cookie = new Cookie("addr", "");
				cookie.setMaxAge(0);  // 초단위
				response.addCookie(cookie); // 추가가 아니라 삭제
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>쿠키삭제하기!!</h1>
	addr 쿠키를 삭제했습니다!!
</body>
</html>