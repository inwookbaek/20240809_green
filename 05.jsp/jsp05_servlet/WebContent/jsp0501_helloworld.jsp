<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- ~:8080/hello -->
	<form action="hello" method="get">
		<input type="submit" value="web.xml-doGet()"/>
	</form>
	<form action="hello" method="post">
		<input type="text" name="name" value="홍길동"/><br>
		<input type="submit" value="web.xml-doPost()"/>
	</form>
	<hr />
	<form action="hello2" method="post">
		<input type="text" name="name" value="홍길동"/><br>
		<input type="submit" value="@WebServlet-doGet/doPost()"/>
	</form>	
</body>
</html>