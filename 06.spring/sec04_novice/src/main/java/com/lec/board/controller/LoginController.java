package com.lec.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "home"; // home.html 반환
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 반환
    }

    @GetMapping("/main")
    public String main() {
        return "main"; // main.html 반환
    }
}