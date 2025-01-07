<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp(servlet 설정)</h1>
	<h4>서블릿을 작동하게 하려면</h4>
	<ol>
		<li>index.jsp를 호출하려면 application.properties에 preffix, suffix를 설정해야한다.</li>
		<li>pre/suffix를 설정하지 않을 경우 index.jsp없이 서블릿을 직접호출(~:8088/login)할 수 있다.</li>
		<li>Main Application에 <b>@ServletComponentScan을 설정</b>해서 서블릿을 빈으로 등록해야 한다.</li>
		<li>@WebServlet(name = "helloServlet", urlPatterns = {"/hello", "/h1", "/h2"})</li>
		<li>@WebServlet(name = "loginServlet", urlPatterns = "/login")</li>
	</ol>	
	<a href="/hello?msg=안녕?">/hello</a><br>
	<a href="/h1?msg=반가워">/h1</a><br>
	<a href="/h2?msg=밥은 먹었니?">/h2</a><br>
	<a href="/login?id=hong&pw=12345">/login</a><br>
</body>
</html>