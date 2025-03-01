<%@page import="java.io.*"%>
<%@page import="java.net.URL"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>파일읽기(3) - URL객체를 이용한 파일읽기</h1>
	<%
		String resourcePath = "/jsp04_implict/message/notice.txt";
		char[] cbuf = new char[128];
		int len = -1;
		URL url = application.getResource(resourcePath);
		try(InputStreamReader fr = 
				new InputStreamReader(url.openStream(), "utf-8")) {
			while((len=fr.read(cbuf)) != -1) {
				out.println(new String(cbuf, 0, len));
			}			
		} catch(Exception e) {
			out.println("에러내용 = " + e.getMessage());
		}
	%>
</body>
</html>