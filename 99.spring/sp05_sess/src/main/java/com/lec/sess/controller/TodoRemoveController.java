package com.lec.sess.controller;

import java.io.IOException;

import com.lec.sess.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet(name = "todoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));
        log.info("tno: " + tno);

        try{
            todoService.remove(tno);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
        resp.sendRedirect("/todo/list");

    }
}
