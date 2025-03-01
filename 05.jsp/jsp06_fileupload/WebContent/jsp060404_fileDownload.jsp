<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String fileName = request.getParameter("fn");
	
	// 실제로 웹서버를 운영할 경우
	// ServletContext context = getServletContext();
	// String downloadPath = context.getRealPath("upload");
	// out.println(context + "<br>");
	// out.println(downloadPath + "<br>");
	
	String downloadFile = "c:/Temp/" + fileName;
	out.println(downloadFile + "<br>");
	
	// download 작성
	File f = new File(downloadFile);
	byte[] b = new byte[4096];
	FileInputStream fis = new FileInputStream(downloadFile);
	String mimeType = getServletContext().getMimeType(downloadFile);
	out.println(mimeType);
	
	// octect-stream은 8bit로 된 일련의 데이터를 의미
	if(mimeType == null) mimeType = "application/octect-stream";
	
	// 한글일 경우 한글깨짐 방지
	String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1");
	String sEncoding1 = URLEncoder.encode(fileName, "utf-8");
	
	response.setContentType(mimeType);
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding1);
	
	ServletOutputStream sos = response.getOutputStream();
	int numRead;
	
	while((numRead = fis.read(b, 0, b.length)) != -1) {
		sos.write(b, 0, numRead);
	}
	
	sos.flush();
	sos.close();
	fis.close();
%>













