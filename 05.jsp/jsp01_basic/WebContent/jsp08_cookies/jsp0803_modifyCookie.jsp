<%@page import="java.net.URLEncoder"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies !=null && cookies.length > 0) {
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("addr")) {
				Cookie cookie 
					= new Cookie("addr", URLEncoder.encode("서울서초구", "utf-8"));
				response.addCookie(cookie); // 쿠키가 있다면 추가가 아니라 수정
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
	<h1>쿠키값변경하기</h1>
</body>
</html>