<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.b_project.model.PageInfo"%>
<%@page import="com.b_project.model.fundingReq.model.FundingReq"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	ArrayList<FundingReq> articleList = (ArrayList<FundingReq>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	
%>
<!DOCTYPE>
<html>
<head>
	<title>펀딩 게시판</title>
	<jsp:include page="/includee/init.jsp"/>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<c:if test="${authUser.id != 'admin'}">
			<div class="col-sm-2"></div>
		</c:if>
		<c:if test="${authUser.id == 'admin'}">
			<jsp:include page="/includee/adminAside.jsp"/>
		</c:if>
		<div class="col-sm-8 main-content">
			<h2 style="font-weight: bold; text-align: left;">펀딩 오픈 게시판</h2>
			<br/>
			<div style="height: 500px;">
			<table class="table list">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>			
				<tbody>
					<c:if test="${!empty articleList}">
						<%
							SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss ");
							for(FundingReq req : articleList) {				
						%>
						<tr>
							<td style="width: 10%"><%=req.getReqNo() %></td> <!--번호 -->
							<td style="text-align: left;">
								<a href="${ctxPath}/fundingReqDetail.do?req_no=<%=req.getReqNo()%>&page=<%=nowPage%>">
									<%=req.getSubject() %>
								</a>			
							</td> <!--컨텐츠와 연결되는 제목-->
							<td style="width: 28%"><%=fmt.format(req.getWrtDate()) %></td> <!--작성일-->
						</tr>
						<% 
							}
						%>
					</c:if>
					<c:if test="${empty articleList}">
						<tr>
							<td colspan="5"><br/>등록된 글이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>			
				<tfoot>	
					<c:if test="${!empty articleList}">
					<tr>				
						<td colspan="5">
							<% if(nowPage <= 1) { %>
								<button class="btn btn-primary" disabled="disabled" style="float: left; margin: 20px; font-size: 12px">&lt</button>
							<% } else { %>
								<a href="${ctxPath}/fundingReqList.do?page=<%=nowPage-1%>" class="btn btn-primary" style="float: left; margin: 20px; font-size: 12px">&lt</a>	<!-- 이전 -->
							<% } %>
					
							<% if(nowPage >= maxPage) { %>
								<button class="btn btn-primary" disabled="disabled" style="float: right; margin: 20px; font-size: 12px">&gt</button>
							<% } else { %>
								<a href="${ctxPath}/fundingReqList.do?page=<%=nowPage+1%>" class="btn btn-primary" style="float: right; margin: 20px; font-size: 12px">&gt</a> 	<!-- 다음 -->
							<% } %>
						</c:if>	
						</td>				
					</tr>			
					<tr>
						<td colspan="5" style="border: 0">	
							<div class="col-sm-12">
								<a href="${ctxPath}/fundingReq.do" class="btn btn-default" style="float: right;">
								<span class="glyphicon glyphicon-pencil"></span>쓰기</a>						
							</div>
						</td>
					</tr>		
				</tfoot>
			</table>
			</div>
		</div>
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	<jsp:include page="/includee/footer.jsp"/>
</body>
</html>