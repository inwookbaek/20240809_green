<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>c:catch tag</h1>
	
	<c:catch var="e">
		name 파라미터의 값 = <%= request.getParameter("name") %><br>
		<%
			if(request.getParameter("name").equals("test")) {
		%>
				파라미터 name의 값은 ${ param.name } 입니다!!
		<%
			}
		%>
	</c:catch>
	<hr />
	
	<c:if test="${ e != null }">
		익셉션이 발생했습니다!!<br>
		${ e }
	</c:if>
</body>
</html>












