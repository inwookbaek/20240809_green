<%@tag body-content="empty" pageEncoding="utf-8" %>
<%@tag trimDirectiveWhitespaces="true" %>
<%@attribute name="title" required="true" fragment="true" %>
<%@attribute name="level" required="false" type="java.lang.Integer" %>
<!-- 
	fragement의 속성값이 true일 경우 JSP는 속성에 값을 전달할 때
	jsp:attribute라는 액션태그를 사용해야 한다.
	
	jsp:attribure태그는 name, trim이라는 속성을 갖는다.
	1. name : 속성의 이름을 지정(필수)
	2. trim : 속성값의 앞뒤의 공백문자(' ', \r, \n, \t)을 제거여부를 설정
	          기본값이 true
-->
<%
	String headerStartTag = null;	
	String headerEndTag = null;
	
	if(level==null) {
		headerStartTag ="<h1>";
		headerEndTag ="</h1>";
	} else if(level==1) {
		headerStartTag ="<h1>";
		headerEndTag ="</h1>";		
	} else if(level==2) {
		headerStartTag ="<h2>";
		headerEndTag ="</h3>";		
	} else if(level==3) {
		headerStartTag ="<h3>";
		headerEndTag ="</h3>";				
	}
%>
<%= headerStartTag %><jsp:invoke fragment="title"/><%= headerEndTag %>