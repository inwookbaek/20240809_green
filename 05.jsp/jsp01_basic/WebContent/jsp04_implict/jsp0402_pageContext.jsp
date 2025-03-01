<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	pageContext기본객체
	
	pageContext기본객체는 JSP페이지와 일대일로 연결된 객체로서
	1. 기본객체구하기
	2. 속성처리하기
	3. 페이지흐름제어하기
	4. 에러데이터구하기
	
	pageContext기본객체 메서드
	
	1. getRequest() : request의 기본객체를 구한다
	2. getResponse() : response의 기본객체를 구한다.
	3. getSession() : session기본객체를 구한다.
	4. getServletContext() : application기본객체를 구한다.
	5. getServletConfig() : config의 기본객체를 구한다.
	6. getOut() : out기본객체를 구한다
	7. getPage() : page의 기본객체를 구한다.
	8. getException : exception기본객체를 구한다.
	
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>pageContext 기본객체</h1>
	<%
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
	%>
	<h3>request와 req(pageContext.getRequest()) 동일객체인지?</h3>
	request == req : <br>
	  1. <%= request == req %><br>
	  2. <%= req.toString() %><br>
	  3. <%= request.toString() %><br>
	
	<h3>pageContext.getOut()</h3>
	out == (pageContext.getOut()) : <%= out == (pageContext.getOut()) %><br>
	<%
		JspWriter out1 = pageContext.getOut();
		out1.println("<h4>Hello JspWriter!!!</h4>");
	%>
</body>
</html>















