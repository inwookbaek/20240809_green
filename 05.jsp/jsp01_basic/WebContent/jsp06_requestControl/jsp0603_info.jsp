<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- localhost:8088/jsp0603_info.jsp  -->
	<h1>jsp에서 파라미터 전달하기</h1>
	<table width="100%" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td>제품번호</td>
			<td>XXX-YYY-ZZ</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>10,000원</td>
		</tr>	
	</table>
	
	<jsp:include page="./jsp0603_infoSub.jsp" flush="false">
		<jsp:param name="type" value="B"/>
	</jsp:include>
</body>
</html>