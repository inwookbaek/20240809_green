<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Layout</h1>
	<table width="400" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
				<jsp:include page="./module/top.jsp" flush="false"></jsp:include>
			</td>
		</tr>
		<tr>
			<td width="100" valign="top">			
				<jsp:include page="./module/left.jsp" flush="false"></jsp:include>
			</td>
			<td width="300" valign="top">
				본문글 레이아웃.....<br><br><br>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="./module/bottom.jsp" flush="false"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>