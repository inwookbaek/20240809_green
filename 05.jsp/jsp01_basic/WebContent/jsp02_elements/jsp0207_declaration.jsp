<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 실습. add(), sub(), mul(), div() 메서드  -->
<%! // 메서드작성
	public int add(int a, int b) { return a + b; }
	public int sub(int a, int b) { return a - b; }
	public int mul(int a, int b) { return a * b; }
	public int div(int a, int b) { return a / b; }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	
	add(10,10) = <%= add(10,10) %><br>
	sub(10,10) = <%= sub(10,10) %><br>
	mul(10,10) = <%= mul(10,10) %><br>
	div(10,10) = <%= div(10,10) %><br>
	<hr />
	10 곱하기 20은 <%= mul(10,20) %> 입니다.<br>
	<hr />
	<%
		int val1 = 10;
		int val2 = 20;
		
		int result = add(val1, val2);
	%>
	<%= val1 %> + <%= val2 %> = <%= result %> 
	
</body>
</html>












