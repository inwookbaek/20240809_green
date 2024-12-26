package com.lec.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// @WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		out.println("<html><body>");
		
		String contentType = req.getContentType();
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			this.printPartInfo(req, out);
		} else {
			out.print("<h3 style='color: red;'>컨텐츠타입이 multipart타입이 아닙니다!!</h3>");
		}
		
		out.println("</body></html>");
	}

	private void printPartInfo(HttpServletRequest req, PrintWriter out) 
			throws IOException, ServletException {
		// out.print("<h3 style='color: blue;'>컨텐츠타입이 multipart타입 입니다!!</h3>");
		String fileName = "dummy";
		int lastIndex = 0;
		
		// 첨부파일이 문자열이 아니라 바이너리데이터이기 때문에 처리하는 방법이 다르다.
		// Servlet3.0이후에 제공되는 Part 인터페이스의 getPart, getParts, getName...등의
		// 메서드를 이용해서 input데이터를 처리할 수 있다.
		Collection<Part> parts = req.getParts();
		
		for(Part part:parts) {
			out.println("<br> name = " + part.getName());  // 파일명추출
			out.println("<br> contentType = " + part.getContentType()); // 컨텐츠타입
			if(part.getHeader("Content-Disposition").contains("filename=")) {
				out.println("<br> size = " + part.getSize());  // 파일의 크기
				
				fileName = getFileName(part);
				// System.out.println(fileName);
				lastIndex = fileName.lastIndexOf("\\");
				fileName = fileName.substring(lastIndex+1);
				out.println("<br> filename = " + fileName); // 순수파일명만 추출
				
				if(part.getSize() > 0) {
					part.write(fileName);
					part.delete();
				}
			} else {
				String value = req.getParameter(part.getName());
				out.println("<br> value = " + value);
			}
		}
	}

	private String getFileName(Part part) {
		// filename="tomcat-users.xml"; filename="web.xml"
		
		for(String cd:part.getHeader("Content-Disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf("=")+1).trim().replace("\"", "");
			}
		}
		return null;
	}	
}

















