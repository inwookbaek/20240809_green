<%@page import="java.io.InputStream"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// fileupload하는 로직을 작성
	request.setCharacterEncoding("utf-8");
	InputStream is = null;
	out.print("[");
	out.print(request.getContentType());
	out.print("]");
	try {
		is = request.getInputStream();
		int data = -1;
		while((data = is.read()) != -1) {
			out.print((char) data);
		}
	} finally {
		if(is != null) try { is.close(); } catch(Exception e) {}
	} 
%>
