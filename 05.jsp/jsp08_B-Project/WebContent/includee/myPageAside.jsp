<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

${ctxPath = pageContext.request.contextPath; ''}

<div class="col-sm-2 aside">
	<h3 style="font-weight: bold;">마이페이지</h3>
	<table class="table">		
		<tr>
			<td onclick="location.href='${ctxPath}/memberDetail.do?id=${authUser.id}'">회원 정보 관리</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/fundingList.do?id=${authUser.id}'">내 후원 목록</td>
		</tr>
		<%-- <tr>
			<td onclick="location.href='${ctxPath}/fundingReqList.do'">펀딩 오픈 신청 이력</td>
		</tr> --%>
	</table>
</div>