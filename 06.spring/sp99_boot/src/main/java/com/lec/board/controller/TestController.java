package com.lec.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.domain.Member;
import com.lec.board.service.MemberService;

@RestController
public class TestController {

    @Autowired
    MemberService memberService;

    @GetMapping("/test")
    public List<Member> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return members;
    }
}