<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.b_project.model.project.model.ProjectBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ProjectBoardBean boardBean = (ProjectBoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
	String searchOption = "";
	String searchWord = "";
	String orderBy = "";
	String asc = "";
	
	if(boardBean.getAttachedFile() == null) {
		boardBean.setAttachedFile("default.jpg");
	}
	if(request.getParameter("search-option") != null && !request.getParameter("search-option").isEmpty()) {
		searchOption = request.getParameter("search-option");
	}
	if(request.getParameter("search-word") != null && !request.getParameter("search-word").isEmpty()) {
		searchWord = request.getParameter("search-word");
	}
	if(request.getParameter("orderBy") != null && !request.getParameter("orderBy").isEmpty()) {
		orderBy = request.getParameter("orderBy");
	}
	if(request.getParameter("asc") != null && !request.getParameter("asc").isEmpty()) {
		asc = request.getParameter("asc");
	}

	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
	
	Date today = new Date();
	long diff = boardBean.getEndDate().getTime() - today.getTime();
	long diffDays = diff/ (24 * 60 * 60 * 1000);
	
	long progress = ((long)boardBean.getNowFund()*100)/boardBean.getObjFund();
%>

<!DOCTYPE>
<html>
<head>
	<title></title>
	
	<jsp:include page="/includee/init.jsp"/>
	
	<style>
		h1,h4{
			font-weight: bold;
		}
		
		#category {
			text-align: center;
			border: thin solid #C2C2C2;
			border-radius: 5px;
			font-weight: bold;
			margin: 0 auto;
			padding: 5px;
			width: 100px;
		}
		
		.col-sm-7 {
			padding: 0;
		}
	</style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	<div class="container" style="margin: 70px auto;">
		<div id="category"><%=boardBean.getCategory() %></div>
		<h1 style="text-align: center;"><%=boardBean.getSubject()%></h1>
		<br />
		<h4 style="text-align: center;">창작자 : <%=boardBean.getCreator() %></h4>
		<br />
		<div class="col-sm-7">
			<img src="${ctxPath}/attachedFile/<%=boardBean.getAttachedFile()%>" 
				 alt="<%=boardBean.getAttachedFile()%>"
				 width="620"
				 height="465"/>
		</div>
		<div class="col-sm-5" style="padding-left: 50px; font-weight: bold;">
			모인 금액
			<h1><%=fmt.format(boardBean.getNowFund())%> (<%=progress%>%)</h1>
			<br />
			남은 시간
			<% if(diffDays >=0) { %>
				<h1><%=diffDays%>일</h1>
			<% } else { %>
				<h1 style="font-weight: bold; color: red;">마감</h1>
			<% } %>
			<br />
			후원자
			<h1><%=boardBean.getSupporterNo() %>명</h1>
			<div style="height: 125px;"></div>
			<c:choose>
				<c:when test="${param.finishedOrNot == 'true' }">
					<button class="btn okay" data-toggle="modal" data-target="#support" disabled="disabled">프로젝트 후원하기</button>
				</c:when>
				<c:otherwise>
					<button class="btn okay" data-toggle="modal" data-target="#support">프로젝트 후원하기</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<!-- 프로젝트 후원 모달 창 -->
	<div id="support" class="modal fade" role="dialog">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
         			<h4 class="modal-title">프로젝트 후원하기</h4>
      			</div>
      			<div class="modal-body">
      				<table>
      					<tr>
      						<td>제목</td>
      						<td>&nbsp;: <%=boardBean.getSubject() %></td>
      					</tr>
      					<tr>
      						<td>창작자</td>
      						<td>&nbsp;: <%=boardBean.getCreator() %></td>
      					</tr>
      					<tr>
      						<td>모인금액</td>
      						<td>&nbsp;: <%=boardBean.getNowFund() %>원 (<%=progress %>%)</td>
      					</tr>
      					<tr>
      						<td>종료일자</td>
      						<td>&nbsp;: <%=boardBean.getEndDate() %></td>
      					</tr>
      				</table>
      			</div>
      			<form action="${ctxPath}/fundingSupport.do" method="post">
      				<input type="hidden" name="project_no" value="<%=boardBean.getProjectNo()%>"/>
      				<input type="hidden" name="page" value=<%=nowPage %> />
      				<div class="modal-footer form-group">
      					<div class="col-sm-6" style="padding: 0 0 20px 0px;">
      						<input type="number" class="form-control" name="money" placeholder="후원하실 금액을 입력해주세요.(\)"/>
      					</div>
      					<div class="col-sm-6" style="padding: 0 0 20px 10px;">
      						<c:if test="${authUser.idStartsWithNumber()}">
      						<input type="password" class="form-control" name="password" placeholder="이메일 주소를 입력해주세요." />	
      						</c:if>
      						<c:if test="${!authUser.idStartsWithNumber()}">
      						<input type="password" class="form-control" name="password" placeholder="비밀번호를 입력해주세요." />
      						</c:if>
      					</div>
      					<div style="float: right;">
      						<input type="submit" class="btn btn-primary" value="확인" />
        					<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
      					</div>	
      				</div>
      			</form>
    		</div>
		</div>
	</div>
	
	<div class="container-fluid" style="background-color: #f0f0f0;">
		<div class="container" style="margin-bottom: 70px;">
			<div style="border-top: 2px solid #C2C2C2;
						border-bottom: 2px solid #C2C2C2; 
						padding: 30px;">
				<%=boardBean.getContent() %>
			</div>
			<p style="float: right; margin: 5px 10px;">작성일 : <%=dateFmt.format(boardBean.getWrtDate()) %> / 수정일 : <%=dateFmt.format(boardBean.getModDate()) %></p>
			<div style="padding: 50px 30px;">
				<a class="btn btn-default" style="float: right; margin-left: 10px;"   
				   href="${ctxPath}/projectDisplay.do?page=<%=nowPage%>&search-option=<%=searchOption%>&search-word=<%=searchWord%>&orderBy=<%=orderBy%>&asc=<%=asc%>">
				   	<span class="glyphicon glyphicon-list"></span> 
				   	목록
				</a>
				<c:if test="${authUser.id=='admin'}">
					<button class="btn btn-default" style="float: right; margin-left: 10px;" onclick="deleteConfirm();">
						삭제
					</button>
					<a class="btn btn-default" style="float: right; margin-left: 10px;"   href="${ctxPath}/projectModify.do?project_no=<%=boardBean.getProjectNo()%>">
						수정
					</a>
					<a class="btn btn-default" style="float: right; margin-left: 10px;"   href="${ctxPath}/projectWrite.do"><span class="glyphicon glyphicon-pencil"></span> 
						쓰기
					</a>
				</c:if>
			</div>		
		</div>
	</div>

  	<jsp:include page="/includee/footer.jsp"/>
  	
  	<script>
  		function deleteConfirm(){
  			if (confirm("정말 삭제하시겠습니까?") == true){    //확인
  				location.href="${ctxPath}/projectDelete.do?project_no=<%=boardBean.getProjectNo()%>";
  			} else {   //취소
  		    	return;
  			}
  		}
  	</script>
</body>
</html>