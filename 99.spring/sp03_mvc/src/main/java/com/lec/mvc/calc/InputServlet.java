package com.lec.mvc.calc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "inputServlet", urlPatterns = "/input")
public class InputServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/calc/input.jsp");
		dispatcher.forward(req, res);
	}
}
