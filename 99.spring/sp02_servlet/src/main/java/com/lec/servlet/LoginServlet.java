package com.lec.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HelloServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher(String.format("/WEB-INF/views/login.jsp?id=%s&pw=%s", id, pw));
		dispatcher.forward(req, res);
	}

}
