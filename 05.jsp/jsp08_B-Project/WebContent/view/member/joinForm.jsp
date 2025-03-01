<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title>회원가입</title>

	<jsp:include page="/includee/init.jsp"/>
	
	<style>
   	input {
    	width: 240px;
    }
    
    input:hover {
    	border-color: #FF4543;
    }
    
    form p {
    	color: #FF4543;
    	margin-top: 5px;
    }
	</style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid" style="background-color: #f7f7f7;">
		<div class="form-group-lg center-form">
			<form action="${ctxPath}/join.do" method="post">
				<label for="id">아이디 *</label>
				<input type="text" name="id" id="id" class="form-control" autofocus="autofocus" placeholder="아이디를 입력해주세요"/>
				<c:if test="${errors.id}"><p>아이디를 입력하세요</p></c:if>
				<c:if test="${errors.duplicatedId}"><p>이미 사용 중인 아이디입니다</p></c:if>
				<c:if test="${errors.startWithNumber}"><p>아이디는 숫자로 시작할 수 없습니다</p></c:if>
				<br />
				
				<label for="name">이름 *</label>
				<input type="text" name="name" id="name" class="form-control" placeholder="이름을 입력해주세요"/>
				<c:if test="${errors.name}"><p>이름을 입력하세요</p></c:if>
				<br />
				
				<label for="password">비밀번호 *</label>
				<input type="password" name="password" id="password" class="form-control"/>
				<c:if test="${errors.password}"><p>비밀번호를 입력하세요</p></c:if>
				<br />
				
				<label for="password">비밀번호 확인 *</label>
				<input type="password" name="confirmPassword" id="confirmPassword" class="form-control"/>
				<c:if test="${errors.confirmPassword}"><p>확인 비밀번호를 입력하세요</p></c:if>
				<c:if test="${errors.noMatch}"><p>비밀번호가 일치하지 않습니다</p></c:if>
				<br />		
				
				<label for="tel">전화번호</label>
				<input type="tel" name="tel" id="tel" class="form-control"/>
				<br />
				
				<label for="eMail">이메일 *</label>
				<input type="email" name="eMail" id="tel" class="form-control"/>
				<c:if test="${errors.eMail}"><p>이메일을 입력하세요</p></c:if>
				<br />
				<p style="color: black; font-weight: bold;">"*" 항목은 필수 입력 항목입니다.</p>
				<br />
				
				<input type="submit" class="btn okay" value="회원 가입" />
			</form>
		</div>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>