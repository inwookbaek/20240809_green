package com.lec.mvc.todo.controller;

import java.io.IOException;

import com.lec.mvc.todo.dto.TodoDTO;
import com.lec.mvc.todo.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
        // /todo/detail?tno=123
        Long tno = Long.parseLong(req.getParameter("tno"));
        TodoDTO dto = TodoService.INSTANCE.get(tno);
        System.out.println("====> " + dto);

        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/WEB-INF/views/todo/read.jsp").forward(req,res);
	}
}
