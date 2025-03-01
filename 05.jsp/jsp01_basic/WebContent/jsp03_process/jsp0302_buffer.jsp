<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page buffer="8kb" autoFlush="true" %>
<!--  

	플러쉬(flush)
	
	버퍼가 다 찼을 떄, 버퍼에 쌓인 데이터를 실제로 전송되어야 할 곳(저장되어야 할 곳)에
	전송하고 버퍼를 비우는 것을 플러쉬라고 한다.
	
	페이지디렉티브는 autoFlush속성을 제공한다. 
	true : 버퍼가 다 차면 버퍼를 플러쉬하고 계속해서 작업을 진행(기본값)
	false: 버퍼가 다 차면 익셉션을 발생시키고 작업을 중지

-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		for(int i=0;i<=100000;i++) {
	%>
			1234567890
	<%
		}
	%>
</body>
</html>










