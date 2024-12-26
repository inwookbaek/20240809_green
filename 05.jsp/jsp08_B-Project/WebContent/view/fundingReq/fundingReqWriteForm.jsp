<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<!DOCTYPE>
<html>
<head>
	<title>펀딩 오픈 신청</title>
  	
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
        	<form action="${ctxPath}/fundingReq.do" enctype="multipart/form-data" method="post">
        		<h2 style="font-weight: bold">펀딩 오픈 신청</h2>
        		<br />
	        	<table class="table summernote">
	        		<tr>
	        			<td style="widows: 15%;"><label for="subject">제목</label></td>
	        			<td colspan="2">
	        				<input type="text" name="subject" id="subject" class="form-control input-sm" placeholder="프로젝트 제목을 입력해주세요" required="required"/>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="3">
	        				<textarea name="content" id="summernote"></textarea>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td><label for="file">파일 첨부</label></td>
	        			<td>
	        				<input type="file" name="file" id="file" required="required"/>
	        			</td>
	        			<td>
	        				<input type="submit" style="float: right; width: 60px; margin-left: 10px;" value="확 인" />
	        				<input type="reset" style="float: right; width: 60px"  value="다시 작성" />
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