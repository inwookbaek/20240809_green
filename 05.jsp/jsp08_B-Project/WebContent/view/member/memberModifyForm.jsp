<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

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
				회원 정보 변경
			</h2>
			<div class="form-group-lg info-form">
				<form action="${ctxPath}/memberModify.do" method="post">
					<label for="id">아이디</label>
					<input type="text" id="id" class="form-control" value="${param.id}" disabled="disabled"/>
					<input type="hidden" name="id" value="${param.id}"/>
					<br />
					<label for="name">이름</label>
					<input type="text" name="name" id="name" class="form-control" value="${param.name}" required="required"/>
					<br />
					<label for="tel">전화번호</label>
					<input type="tel" name="tel" id="tel" class="form-control" value="${param.tel}"/>
					<br />	
					<label for="eMail">이메일</label>
					<input type="email" name="eMail" id="eMail" class="form-control" value="${param.eMail}" required="required"/>
					<br />
					<label for="regDate">가입일자</label>
					<input type="text" name="regDate" id="regDate" class="form-control" value="${param.regDate}" disabled="disabled"/>
					<br />
					<label for="level">등급</label>
					<input type="text" name="level" id="level" class="form-control" value="${param.level}" disabled="disabled"/>
					<br />
					<br />
					<c:if test="${authUser.id != 'admin'}">
						<input type="submit" class="btn okay" style="width: 70px; float: right;" value="확인" />
					</c:if>
				</form>
				<c:if test="${authUser.id != 'admin'}">
					<button class="btn okay" data-toggle="modal" data-target="#withdraw" style="width: 70px; float: right; margin-right: 20px;">탈퇴</button>
				</c:if>
				<c:if test="${authUser.id == 'admin'}">
					<button class="btn okay" data-toggle="modal" data-target="#withdraw" style="width: 70px; float: right;">추방</button>
				</c:if>
				<br />
				<br />
			</div>
		</div>
		
		<!-- 탈퇴 시 비번 확인 모달 창 -->
		<div id="withdraw" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<c:if test="${authUser.id != 'admin'}">
							<h4 class="modal-title">회원 탈퇴하기</h4>
						</c:if>
						<c:if test="${authUser.id == 'admin'}">
							<h4 class="modal-title">회원 추방하기</h4>
						</c:if>
					</div>
					<form action="${ctxPath}/memberDelete.do" id="withdrawConfirm" method="post">
						<div class="modal-body" style="height: 100px;">
							<c:if test="${authUser.idStartsWithNumber()}">
							<p>이메일 주소를 입력해주세요.</p>
							</c:if>
							<c:if test="${!authUser.idStartsWithNumber()}">
							<p>비밀번호를 입력해주세요.</p>
							</c:if>
							<div class="col-sm-10" style="padding: 0;">
								<input type="hidden" name="id" value="${param.id}" />
								<input type="password" name="password" class="form-control" required="required"/>
							</div>
							<div class="col-sm-2" style="padding: 0;">
								<button type="button" class="btn btn-danger" onclick="withdrawConfirm();" style="float: right;">확인</button>
							</div>	
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
	
	<script>
		function withdrawConfirm() {
			var stmt;
			
			if(${authUser.id == 'admin'}) {
				stmt = "정말 추방하시겠습니까?";	
			} else {
				stmt = "정말 탈퇴하시겠습니까?";
			}
			
			if(confirm(stmt) == true) {
				document.getElementById('withdrawConfirm').submit();
				return false;
			}
		}
	</script>
</body>
</html>