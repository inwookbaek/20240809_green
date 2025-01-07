<%@ page import="com.b_project.model.fundingReq.model.FundingReq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	${ctxPath = pageContext.request.contextPath; ''}
	
<% 
	FundingReq article = (FundingReq)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE>
<html>
<head>
	<title>글 수정하기</title>
	
	<jsp:include page="/includee/init.jsp"/>
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	
	<style>
  	input:hover, textarea:hover, select:hover {
    	border-color: #FF3533;
    }
    </style>
</head>
<body>
	<jsp:include page="/includee/navbar.jsp"/>
	
	<div class="container-fluid main-container">
		<div class="col-sm-2"></div>
	
		<div class="col-sm-8 main-content text-left">
			<form action="${ctxPath}/fundingReqModify.do" enctype="multipart/form-data" method="post">
				<h2 style="font-weight: bold">펀딩 오픈 신청 수정</h2>
        		<br />
        		<input type="hidden" name="page" value="<%=nowPage %>" />
				<input type="hidden" name="req_no" value="<%=article.getReqNo()%>"/>
	        	<table class="table summernote">
	
					<tr>
	        			<td style="widows: 15%;"><label for="subject">제목</label></td>
	        			<td colspan="2">
	        				<input type="text" name="subject" id="subject" class="form-control input-sm" placeholder="프로젝트 제목을 입력해주세요" required="required" value="<%=article.getSubject()%>"/>
	        			</td>
	        		</tr>
					<tr>
						<td colspan="3">
	        				<textarea name="content" id="summernote"><%=article.getContent()%></textarea>
	        			</td>
					</tr>
					<tr>
						<td><label for="openFundingFile">첨부파일</label></td>
						<td colspan="3">
	        				<input type="file" name="openFundingFile" id="openFundingFile"/>
	        			</td>
						<td>
							<% if(article.getFile() != null) { %>
								<a href="file_down?downFile=<%=article.getFile()%>"></a>
							<% } else { %>
								첨부파일이 없습니다.
							<% } %>		
						</td>
						<td>
							<input type="submit" class="btn btn-default" style="float: right; width: 60px" value="수정"/>		
					 	</td>	
					</tr>
				</table>
			</form>	
		</div>
		<jsp:include page="/includee/ads.jsp"/>
	</div>
	<jsp:include page="/includee/footer.jsp"/>
	
	<script>
		$(document).ready(function() {
     		$('#summernote').summernote({
             	height: 450,                 // set editor height
             	minHeight: null,             // set minimum height of editor
             	maxHeight: null,             // set maximum height of editor
             	focus: true,                 // set focus to editable area after initializing summernote
             	disableResizeEditor: true
     		});
		});
	</script>
</body>
</html>