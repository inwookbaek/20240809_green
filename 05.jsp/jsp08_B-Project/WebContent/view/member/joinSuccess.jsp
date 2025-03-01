<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title>회원 가입 성공</title>
</head>
<body>
	<script>
		alert("회원 가입에 성공했습니다!");
		document.location.href = "${ctxPath}/view/auth/loginForm.jsp";
	</script>
</body>
</html>