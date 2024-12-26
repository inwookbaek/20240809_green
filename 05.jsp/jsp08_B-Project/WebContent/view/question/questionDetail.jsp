<%@page import="com.b_project.model.question.model.Answer"%>
<%@page import="com.b_project.model.member.model.Member"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.question.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Question question = (Question)request.getAttribute("article");
	Answer answer = (Answer)request.getAttribute("answer");
	String nowPage = (String)request.getAttribute("page");
	String searchOption = "";
	String searchWord = "";
	String faq = "";
	String id = "";
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
	
	if(request.getParameter("search-option") != null && !request.getParameter("search-option").isEmpty()) {
		searchOption = request.getParameter("search-option");
	}
	if(request.getParameter("search-word") != null && !request.getParameter("search-word").isEmpty()) {
		searchWord = request.getParameter("search-word");
	}
	if(request.getParameter("faq") != null && !request.getParameter("faq").isEmpty()) {
		faq = request.getParameter("faq");
	}
	if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		id = request.getParameter("id");
	}
%>
<!DOCTYPE>
<html>
<head>
	<title></title>
	
	<jsp:include page="/includee/init.jsp"/>
	
	<style>
		.answer-sheet {
			background-color: #F8FFFF; 
			margin: 20px;
			padding: 20px;
			border-radius: 3px;
			border-top: 3px solid #4263EB;
		}
	</style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		
		<jsp:include page="/includee/questionAside.jsp"/>
		
		<div class="col-sm-8 main-content"> 
			<h2 style="font-weight: bold; text-align: left;">
      			<% if(id != null && !id.isEmpty()) { %>
      				나의 상담 내역
      			<% } else if(faq.equals("true")) { %>
      				자주 묻는 질문
      			<% } else { %>
      				질문과 답변
      			<% } %>
      		</h2>
      		<br />
			<div style="height: 600px;">
				<table class="table board-detail">
					<tr>
						<td>제목</td>
						<td>: &nbsp;&nbsp;<%=question.getSubject()%></td>
						<td>작성일</td>
						<td>: &nbsp;&nbsp;<%=fmt.format(question.getWrtDate())%></td>
					</tr>
					<tr>
						<td width="10%" style="border-bottom: thin solid #777777;">작성자</td>
						<td width="45%" style="border-bottom: thin solid #777777;">:
							&nbsp;&nbsp;
							<% if(question.getId() != null) { %>
								<%=question.getName()%>(<%=question.getId()%>)
							<% } else { %>
								탈퇴 회원
							<% } %>
						</td>
						<td width="10%" style="border-bottom: thin solid #777777;">수정일</td>
						<td width="35%" style="border-bottom: thin solid #777777;">:
							&nbsp;&nbsp;<%=fmt.format(question.getModDate())%></td>
					</tr>
					<tr>
						<td colspan="4"><br /> <%=question.getContent() %> <br /></td>
					</tr>
					<c:if test="${!empty answer}">
					<tr>
						<td colspan="4">
							<div class="answer-sheet">
								<h3><span class="label label-primary">답변입니다.</span></h3>
								
								<p style="float: right; margin-top: -30px;">
									<small>작성일 : <%=fmt.format(answer.getWrtDate())%></small> <br />
								 	<small>수정일 : <%=fmt.format(answer.getModDate())%></small>
								</p>
								<br />
								<p><%=answer.getContent() %></p>
								<br />
								<br />
								<c:if test="${authUser.id == 'admin'}">
								<button class="btn btn-primary" style="float: right; margin: -30px 0 0 10px;" onclick="answerDeleteConfirm();">
									삭제
								</button>
								<a class="btn btn-primary" style="float: right; margin: -30px 0 0 10px;"   href="${ctxPath}/answerModify.do?q_no=<%=answer.getqNo()%>">
									수정
								</a>
								</c:if>
							</div>	
						</td>
					</tr>
					</c:if>
				</table>
				
				<a class="btn btn-default" style="float: right; margin-left: 10px;"   
				   href="${ctxPath}/questionList.do?page=<%=nowPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&faq=<%=faq%>&id=<%=id%>">
					<span class="glyphicon glyphicon-list"></span> 
				   	목록
				</a>
				<c:if test="${authUser.id=='admin'||authUser.id==article.id}">
					<button class="btn btn-default" style="float: right; margin-left: 10px;" onclick="deleteConfirm();">
						삭제
					</button>
				</c:if>		
				<c:if test="${authUser.id==article.id}">
					<a class="btn btn-default" style="float: right; margin-left: 10px;"   href="${ctxPath}/questionModify.do?q_no=<%=question.getqNo()%>">
						수정
					</a>
				</c:if>
				<c:if test="${authUser.id != 'admin'}">
					<a class="btn btn-default" style="float: right; margin-left: 10px;"   href="${ctxPath}/questionWrite.do"><span class="glyphicon glyphicon-pencil"></span> 
						쓰기
					</a>
				</c:if>
				<c:if test="${authUser.id == 'admin'&&empty answer}">
					<a class="btn btn-default" style="float: right; margin-left: 10px;"   href="${ctxPath}/answerWrite.do?q_no=<%=question.getqNo()%>"><span class="glyphicon glyphicon-pencil"></span> 
						답변하기
					</a>
				</c:if>	
			</div>
      	</div>
      	<jsp:include page="/includee/ads.jsp"/>
	</div>
      		
    <jsp:include page="/includee/footer.jsp"/>
    
    <script>
  		function deleteConfirm(){
  			if (confirm("정말 삭제하시겠습니까??") == true){    //확인
  				location.href="${ctxPath}/questionDelete.do?q_no=<%=question.getqNo()%>";
  			} else {   //취소
  		    	return;
  			}
  		}
  		
  		function answerDeleteConfirm(){
  			if (confirm("정말 삭제하시겠습니까??") == true){    //확인
  				location.href="${ctxPath}/answerDelete.do?q_no=<%=question.getqNo()%>";
  			} else {   //취소
  		    	return;
  			}
  		}
  	</script>
</body>
</html>