<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="ko"/>
<fmt:bundle basename="resource.message">
<fmt:message key="TITLE" var="title"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ title }</title>
</head>
<body>
	eng | kor<br>
	<fmt:message key="GREETING"/><br>
	
	<c:if test="${ !empty param.id }">
		<fmt:message key="VISITOR">
			<fmt:param value="${ param.id }"/>
		</fmt:message>
	</c:if>

</body>
</html>
</fmt:bundle>