<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Custom Tag로 제목태그 출력하기(with fragment)</h1>
	<tf:header_frag level="2">
		<jsp:attribute name="title">텍스트제목</jsp:attribute>
	</tf:header_frag>
</body>
</html>