<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>forTokens</h1>
	컴마(,)와 점(.)을 구분자로 사용하기 <br>
	<c:forTokens var="token" items="빨,주,노.초,파.남,보" delims=",.">
		${ token } <br>
	</c:forTokens>
</body>
</html>