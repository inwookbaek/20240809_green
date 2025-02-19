<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>jsp로 페이지이동하기(forward)</h1>
	<%= request.getContextPath() %>/jsp06_requestControl/jsp0607_view.jsp<br>
	<hr />
	
	<!-- <form action="./jsp0607_view.jsp"> -->
	<form action="<%=request.getContextPath()%>/jsp06_requestControl/jsp0607_view.jsp">
		이동할 페이지 선택:
		<select name="code" id="">
			<option value="A">A페이지</option>
			<option value="B">B페이지</option>
			<option value="C">C페이지</option>
		</select>
		<br>
		<input type="submit" value="페이지이동하기"/>
	</form>
</body>
</html>