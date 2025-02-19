package com.lec.sess.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebListener
public class SessAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.info("----------init---------------------------");
        log.info("----------init---------------------------");
        log.info("----------init---------------------------");

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("appName", "Sess");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        log.info("----------destroy---------------------------");
        log.info("----------destroy---------------------------");
        log.info("----------destroy---------------------------");

    }
}
