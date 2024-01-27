package com.jaehak.testsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // 이미 security가 처리해주었기때문에 내가 직접 처리 불필요
    //@PostMapping("/login")
    public String login() {
        return "login";
    }
}