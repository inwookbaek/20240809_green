package com.lec.sess.controller;

import java.io.IOException;
import java.util.List;

import com.lec.sess.dto.TodoDTO;
import com.lec.sess.service.TodoService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

//    1차    
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
//    		throws ServletException, IOException {
//
//        log.info("todo list..................");
//
//        try {
//            List<TodoDTO> dtoList = todoService.listAll();
//            req.setAttribute("dtoList", dtoList);
//            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,res);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new ServletException("list error");
//        }
//    }
    
    // 2차 - 218 : ServletContext
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
    	
    	log.info("todo list..................");
    	
        ServletContext servletContext = req.getServletContext();
        log.info("appName:  "  + servletContext.getAttribute("appName"));
        
    	try {
    		List<TodoDTO> dtoList = todoService.listAll();
    		req.setAttribute("dtoList", dtoList);
    		req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,res);
    	} catch (Exception e) {
    		log.error(e.getMessage());
    		throw new ServletException("list error");
    	}
    }
}
