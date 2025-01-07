<%@page import="com.b_project.model.member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${ctxPath = pageContext.request.contextPath; ''}

<%
	Member user = (Member)request.getSession(false).getAttribute("authUser");
	String id = "";
	if(user != null) {
		id = user.getId();
	}
%>
<div class="col-sm-2 aside">
	<h3 style="font-weight: bold;">고객 센터</h3>
	<table class="table">		
		<tr>
			<td onclick="location.href='${ctxPath}/questionList.do?faq=true'">자주 묻는 질문</td>
		</tr>
		<tr>
			<td onclick="location.href='${ctxPath}/questionList.do'">질문과 답변</td>
		</tr>
		<c:if test="${!empty authUser && authUser.id != 'admin'}">
			<tr>
				<td onclick="location.href='${ctxPath}/questionList.do?id=<%=id%>'">나의 상담 내역</td>
			</tr>
		</c:if>
	</table>
</div>