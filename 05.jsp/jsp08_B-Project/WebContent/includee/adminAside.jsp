<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<div class="col-sm-2 aside">
	<h3 style="font-weight: bold;">관리자 모드</h3>
	<table class="table">		
		<tr>
			<td onclick="location.href='${ctxPath}/projectList.do'">프로젝트 리스트</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/projectList.do?finishedOrNot=true'">마감된 프로젝트 리스트</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/fundingReqList.do'">펀딩 오픈 신청 관리</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/questionList.do'">고객 센터 관리</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/memberList.do'">회원 관리</td>
		</tr>
	</table>
</div>