<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>파일업로드확인 및 다운로드하기</h1>
	<table border="1">
		<tr>
			<td>작성자</td>
			<td>${ param.name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${ param.subject }</td>
		</tr>
		<tr>
			<td>파일1</td>
			<td><a href="jsp060404_fileDownload.jsp?fn=${ param.fileName1 }">${ param.orgFileName1 }</a></td>
		</tr>
		<tr>
			<td>파일2</td>
			<td><a href="jsp060404_fileDownload.jsp?fn=${ param.fileName2 }">${ param.orgFileName2 }</a></td>
		</tr>
	</table>
</body>
</html>