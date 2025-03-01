<%@page import="java.io.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>파일읽기(2) - application기본객체를 사용해서 파일 읽기</h1>
	<h3>Context Path = <%= request.getContextPath() %></h3>
	<%
		String resourcePath = "/jsp04_implict/message/notice.txt";
	%>
	파일의 실제경로 : <%= application.getRealPath(resourcePath) %>
	<hr />
	<%= resourcePath %>의 내용<br>
	<%
		char[] cbuf = new char[128];
		int len = -1;
		try(InputStreamReader fr = 
				new InputStreamReader(
						application.getResourceAsStream(resourcePath), "utf-8")) {
			while((len=fr.read(cbuf)) != -1) {
				out.println(new String(cbuf, 0, len));
			}			
		} catch(Exception e) {
			out.println("에러내용 = " + e.getMessage());
		}
	%>
</body>
</html>











