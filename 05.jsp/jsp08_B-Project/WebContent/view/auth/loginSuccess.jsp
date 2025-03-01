<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title>로그인 성공</title>
</head>
<body>
	<script>
		alert("${authUser.name}님 환영합니다!");
		document.location.href = "${ctxPath}/index.jsp";
	</script>
</body>
</html>