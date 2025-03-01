<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Number Format Tag</h1>
	<c:set var="price" value="10000"/>
	<fmt:formatNumber value="${ price }" type="number" var="numberType "/><br>
	통화   : <fmt:formatNumber value="${ price }" type="currency" currencySymbol="$"/><br>
	퍼센트 : <fmt:formatNumber  value="${ price }" type="percent" groupingUsed="false"/><br>
	숫자   : ${ price }<br>
	패턴   : <fmt:formatNumber value="${ price }" pattern="#,##0.00"/><br>
</body>
</html>