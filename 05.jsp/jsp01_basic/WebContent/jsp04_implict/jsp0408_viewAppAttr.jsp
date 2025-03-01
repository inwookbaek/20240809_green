<%@page import="java.util.Enumeration"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>application의 속성보기</h1>
	<%
		// application.setAttribute("board", new Board());
		Enumeration<String> appAttr = application.getAttributeNames();
		while(appAttr.hasMoreElements()) {
			String name = appAttr.nextElement();
			Object value = application.getAttribute(name);
	%>
			<h3>application 속성 : <%= name %> = <%= value %>
	<%
		}
	%>
</body>
</html>
