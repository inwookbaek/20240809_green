package com.lec.mvc.calc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "calcResult", urlPatterns = "/calcResult")
public class CalcResultServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher(String.format("/WEB-INF/views/calc/calcResult.jsp?num1=%s&num2=%s", num1, num2));
		dispatcher.forward(req, res);
		
	}
	
}
