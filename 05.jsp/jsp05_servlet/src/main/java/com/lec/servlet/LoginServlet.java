package com.lec.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// @WebServlet("/login")
@WebServlet("*.do")
public class LoginServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("====> 1. init() 호출" );
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("====> 3. doGet() 호출" );
		req.setCharacterEncoding("utf-8");
//		res.getWriter().append("Served at: ").append(req.getContextPath());
//		res.getWriter().append("아이디: ").append(req.getParameter("id"));
//		res.getWriter().append("비밀번호: ").append(req.getParameter("pw"));
//		res.getWriter().append("이름: ").append(req.getParameter("name"));
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		
		// System.out.println(req.getRequestURI()); // /jsp05_servlet/*.do
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();  // jsp05_sevrvlet
		String command = requestURI.substring(contextPath.length()+1, requestURI.length()-3); // *.do
		
		// /jsp0503_loginSuccess.jsp?id=id&pw=pw&name=name
		RequestDispatcher dispatcher = null;
		
		if (command.equals("login")) {
			dispatcher = req.getRequestDispatcher(String.format("/jsp0503_loginSuccess.jsp?id=%s&pw=%s&name=%s", id, pw, name));
		} else if(command.equals("list")) {
			dispatcher = req.getRequestDispatcher("/list.jsp");
		} else if(command.equals("write")) {
			dispatcher = req.getRequestDispatcher("/write.jsp");			
		} else if(command.equals("delete")) {
			dispatcher = req.getRequestDispatcher("/delete.jsp");						
		} else {
			dispatcher = req.getRequestDispatcher("/logout.jsp");									
		}
				
		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("====> 2. doPost() 호출" );
		doGet(req, res);
	}

}
