<%@page import="com.b_project.model.fundingReq.model.FundingReq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	${ctxPath = pageContext.request.contextPath; ''}

<% 
	FundingReq article = (FundingReq)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE>
<html>
<head>
	<title>글 내용 확인하기</title>
	
	<jsp:include page="/includee/init.jsp"/>
	
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<div class="col-sm-2"></div>
		<div class="col-sm-8 main-content">
			<h2 style="font-weight: bold">펀딩 오픈 신청 상세</h2>
        	<br />
			
	        <table class="table summernote">
	        	<tr>
        			<td style="width: 15%;"><label for="subject">제목</label></td>
        			<td style="font-weight: bold;">
        				<%=article.getSubject()%>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="2">
        				<div style="background:#ffffff; border-radius: 5px; border: thin solid #c2c2c2; padding: 20px; width: 100%; height: 450px; overflow: scroll;"><%=article.getContent()%></div>
        			</td>
        		</tr>	
				<tr>
					<td><label for="file">파일</label></td>
					<td>
						<% if(article.getFile() != null) { %>
							<a href="filedown.do?downFile=<%=article.getFile()%>"><%=article.getFile()%></a>
						<% } else { %>
							첨부파일이 없습니다.
						<% } %>						
					</td>
				</tr>
				<tr>
		 			<td colspan="2">
						<a href="${ctxPath}/fundingReqList.do?req_no=<%=article.getReqNo()%>&page=<%=nowPage%>" class="btn btn-default" style="float: right; margin-left: 10px;">
								<span class="glyphicon glyphicon-list-alt"></span> 목록
						</a> <!-- 목록 -->
							
						<a href="${ctxPath}/fundingReqModify.do?req_no=<%=article.getReqNo()%>&page=<%=nowPage%>" class="btn btn-default" style="float: right; margin-left: 10px">
							 	<span class="glyphicon glyphicon-cog"></span> 수정
						</a> <!-- 수정 --> 					
								
						<button class="btn btn-default" style="float: right;" onclick="deleteConfirm();">
							 <span class="glyphicon glyphicon-remove"></span> 삭제
						</button> <!-- 삭제 --> 		
					</td>	
				</tr>
			</table>
		</div>
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	
	<jsp:include page="/includee/footer.jsp"/>
	
	<script>
  		function deleteConfirm(){
  			if (confirm("정말 삭제하시겠습니까?") == true){    //확인
  				location.href="${ctxPath}/fundingReqDelete.do?req_no=<%=article.getReqNo()%>";
  			} else {   //취소
  		    	return;
  			}
  		}
  	</script>
</body>
</html>