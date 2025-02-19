<%@page import="java.io.FileReader"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>out tag - jsp1108_out.jsp</h1>
	<!-- http://localhost:8088/jsp01_basic/jsp11_jstl/jsp1108_out.jsp
		?path=/jsp11_jstl/jsp1102_if.jsp
	 -->
	 <%
	 	FileReader reader = null;
	 	try {
	 		String path = request.getParameter("path");
	 		reader = new FileReader(getServletContext().getRealPath(path));
	 %>
	 		<pre>
	 			소스코드 = <%= path %>
	 			<c:out value="<%= reader %>" escapeXml="true"/>
	 		</pre>
	 <%
	 	} catch(Exception e) {
	 %>
	 		에러 : <%= e.getMessage() %>
	 <%
	 	} finally {
	 		if(reader != null) try { reader.close(); } catch(Exception e) {}
	 	}
	 %>
</body>
</html>