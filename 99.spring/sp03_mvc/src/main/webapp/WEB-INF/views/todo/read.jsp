<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Todo Detail</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>마감일</th>
			<th>종료일</th>
		</tr>
		<tr>
			<td>${ dto.tno }</td>
			<td>${ dto.title }</td>
			<td>${ dto.dueDate }</td>
			<td>${ dto.finished }</td>		
		</tr>
	</table>
</body>
</html>