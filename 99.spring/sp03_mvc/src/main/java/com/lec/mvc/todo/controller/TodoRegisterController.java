package com.lec.mvc.todo.controller;

import java.io.IOException;
import java.util.List;

import com.lec.mvc.todo.dto.TodoDTO;
import com.lec.mvc.todo.service.TodoService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("/WEB-INF/views/todo/register.jsp");
		dispatcher.forward(req, res);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		List<TodoDTO> todoLists = TodoService.INSTANCE.getList();
		System.out.println(todoLists);
		req.setAttribute("todoLists", todoLists);
		req.getRequestDispatcher("/WEB-INF/views/todo/list.jsp").forward(req, res);	
	}
}
