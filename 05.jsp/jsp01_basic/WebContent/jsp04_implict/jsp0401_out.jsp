<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
	기본객체와 영역(Scope)
	
	1. JSP기본객체
	   1) request(javax.servlet.http.HttpServletRequest) : 클라이언트 요청정보를 저장
	   2) response(javax.servlet.http.HttpServletResponse) : 응답정보를 저장
	   3) out(javax.servlet.jsp.JspWriter) : JSP페이지가 생성결과를 출력할 때 사용하는 
	      출력스트림
	   4) pageContext(javax.servlet.jsp.PageContext) : JSP페이지에 대한 정보를 저장
	   5) session(javax.servlet.http.HttpSession) : HTTP 세션정보를 저장
	   6) application(javax.servlet.ServletContext) : 웹애플리케이션에 대한 정보를 저장
	   7) page(java.lang.Object) : JSP페이지를 구현한 자바클래스 인스턴스(객체)임
	   8) config(javax.servlet.ServletConfig) : JSP페이지의 설정정보를 저장
	   9) exception(java.lang.Throwable) : 익셉션객체, 에러페이지에서만 사용가능

	out기본객체는 웹브라우저에 데이터를 전송하는 출력스트림으로서 JSP페이지가 생성한
	데이터를 출력한다. 
	
	out기본객체의 출력메서드는 
	
	1. print() : 데이터를 출력
	2. println() : 데이터를 출력하고 줄바꿈문자(\r\n 또는 \n)를 출력
	3. newLine() : 줄바꿈문자(\r\n 또는 \n)를 출력
	
	out기본객체의 buffer관련메서드
	
	1. getBufferSize() : 버퍼의 크기를 리턴
	2. getRemaining() : 현재 남은 버퍼의 크기를 리턴
	3. clear() : 버퍼의 내용을 비운다. 이미 플러시가 되었다면 익셉션발생
	4. clearBuffer() : 버퍼의 내용을 비운다. 플러시가 되었더라도 익셉션이 발생안함
	5. flush() : 버퍼를 flush한다.
	6. isAutoFlush : 버퍼가 다 찼을 때 자동으로 플로시할 경우 true를 리턴
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	request : <%= request.toString() %><br>
	response : <%= response.toString() %><br>
	out : <%= out.toString() %><br>
	<hr />
	<% out.println("Hello World!!"); %><br>
	out기본객체를 사용하여 <% out.print("출력한 결과입니다!!"); %><br>
	<hr />
	<%
		out.print("<h1>");
		out.print("out(javax.servlet.jsp.JspWriter) 기본객체!!");
		out.print("</h1>");
	%>
	<hr />
	<h3>out객체의 buffer정보</h3>
	버퍼크기 : <%= out.getBufferSize() %><br>
	버퍼잔량 : <%= out.getRemaining() %><br>
	autoFlush: <%= out.isAutoFlush() %><br>
</body>
</html>
















