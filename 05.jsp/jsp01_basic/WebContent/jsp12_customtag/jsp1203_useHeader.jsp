<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Custom Tag로 제목태그 출력하기</h1>
	<tf:header title="테스트제목"/>
	<tf:header title="테스트제목" level="2"/>
	<tf:header title="${'EL제목' }" level="3"/>
	<tf:header title='<%= "표현식제목" %>' level="2"/>
</body>
</html>