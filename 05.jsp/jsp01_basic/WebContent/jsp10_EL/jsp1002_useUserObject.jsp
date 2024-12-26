<%@page import="com.lec.el.Thermometer"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Thermometer t_obj = new Thermometer();
	request.setAttribute("t", t_obj);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>${ t.getInfo() }</h1>
	${ t.setCelsius('서울', 18.5) }
	서울의 온도 : 섭씨 ${ t.getCelsius('서울') } 도<br>
	              화씨 ${ t.getFahrenheit("서울") } 도<br>
</body>
</html>