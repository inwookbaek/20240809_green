package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = {"/hello", "/h1", "/h2"})
public class HelloServlet extends HttpServlet {

	private String message;
	
	@Override
	public void init() throws ServletException {
		message = "안녕하세요? ";
		System.out.println("init() => " + message);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		out.println(String.format("<h1>%s</h1>", message + req.getParameter("msg")));
		out.println(String.format("<h3>%s</h3>", req.getRequestURI()));
	}
}
