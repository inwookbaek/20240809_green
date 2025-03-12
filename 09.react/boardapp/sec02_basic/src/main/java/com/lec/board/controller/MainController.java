package com.lec.board.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
    	
    	String id = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    	GrantedAuthority auth = iterator.next();
    	String role = auth.getAuthority();
    	
    	model.addAttribute("id", id);
    	model.addAttribute("role", role);
   
        return "main";
    }
    
}