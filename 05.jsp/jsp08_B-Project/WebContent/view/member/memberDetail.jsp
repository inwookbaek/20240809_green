<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Member member = (Member)request.getAttribute("member");
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
	String level = null;
	
	switch(member.getLevel()) {
		case 1: level = "새싹멤버"; break;
		case 2: level = "성실멤버"; break;
		case 3: level = "열심멤버"; break;
		case 4: level = "열성멤버"; break;
		case 5: level = "골수멤버"; break;
		case 6: level = "감사멤버"; break;	
	}
%>

<!DOCTYPE>
<html>
<head>
	<title></title>
	
	<jsp:include page="/includee/init.jsp"/>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<c:if test="${authUser.id != 'admin'}">
			<jsp:include page="/includee/myPageAside.jsp"/>
		</c:if>
		<c:if test="${authUser.id == 'admin'}">
			<jsp:include page="/includee/adminAside.jsp"/>
		</c:if>
		
		
		<div class="col-sm-8 main-content"> 
			<h2 style="font-weight: bold; text-align: left;">
				회원 정보 관리
			</h2>
			<div class="form-group-lg info-form">
				<form action="${ctxPath}/memberModify.do">
					<label for="id">아이디</label>
					<input type="text" id="id" class="form-control" value="${member.id}" disabled="disabled"/>
					<input type="hidden" name="id" value="${member.id}"/>
					<br />
					<label for="name">이름</label>
					<input type="text" id="name" class="form-control" value="${member.name}" disabled="disabled"/>
					<input type="hidden" name="name" value="${member.name}"/>
					<br />
					<label for="tel">전화번호</label>
					<input type="tel" id="tel" class="form-control" value="${member.tel}" disabled="disabled"/>
					<input type="hidden" name="tel" value="${member.tel}"/>
					<br />	
					<label for="eMail">이메일</label>
					<input type="email" id="eMail" class="form-control" value="${member.eMail}" disabled="disabled"/>
					<input type="hidden" name="eMail" value="${member.eMail}"/>		
					<br />
					<label for="regDate">가입일자</label>
					<input type="datetime" id="regDate" class="form-control" value="<%=fmt.format(member.getRegDate()) %>" disabled="disabled"/>
					<input type="hidden" name="regDate" value="<%=fmt.format(member.getRegDate()) %>"/>
					<br />
					<label for="level">등급</label>
					<input type="text" id="level" class="form-control" value="<%=level %>" disabled="disabled"/>
					<input type="hidden" name="level" value="<%=level %>"/>
					<br />
					<br />
					<input type="submit" class="btn okay" style="width: 150px; float: right;" value="회원 정보 변경" />
					<br />
					<br />
				</form>
			</div>
		</div>
		
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>