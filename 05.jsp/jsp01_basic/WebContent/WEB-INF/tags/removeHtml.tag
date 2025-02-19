<%@tag body-content="scriptless" pageEncoding="utf-8" %>
<%@attribute name="length" type="java.lang.Integer"%>
<%@attribute name="trail" %>
<%@attribute name="trim" %>
<!-- 
	tag파일에서 몸체내용의 EL이나 액션태그처리결과를 사용하려면
	
	1. tag 디렉티브 body-content속성의 값을 "scriptless"로 지정
	   1) empty      : body를 가질 수가 없다.
	   2) scriptless : 스크립트(스크립트릿, 스크립트표현식, 선언문)을 사용할 수 없다. 
	   3) JSP        : JSP안에 들어갈 수 있는 내용이라면 무엇이든지 가능 
	   
	2. jsp:body 액션태그를 이용해서 몸체 내용을 출력하거나 EL변수로 지정한다.
	
	 body-content의 기본값은 scriptless가 기본값이다. jsp:body는 다음과 같은 형태로 사용한다.
	 
	 1) jsp:doBody : 몸체로 전달받은 내용을 그대로 출력한다.
	 2) jsp:doBody var="변수명" scope="영역" : 몸체로 전달받은 내용을 var의 EL변수에 저장
	    scope속성은 EL변수를 저장할 영역을 지정한다. 기본값은 request영역이다. 
-->
<jsp:doBody var="content" scope="request"/>
<%
	String content = (String) jspContext.getAttribute("content");

 	if(trim != null && trim.equals("true")) {
		content = content.trim();
	} 
	content = content.replace("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?>", "");
	
	if(length != null && length.intValue() > 0 && content.length() > length.intValue()) {
		content = content.substring(0, length.intValue());
		if(trail != null) {
			content = content + trail;
		}
	}
%>
<%= content %>










