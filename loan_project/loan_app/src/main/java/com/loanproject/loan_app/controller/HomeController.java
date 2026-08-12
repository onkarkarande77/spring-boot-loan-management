package com.loanproject.loan_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.loanproject.loan_app.bean.User;
import com.loanproject.loan_app.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public String home(){
    	return "index"; 
    	}

    @GetMapping("/login")
    public String login(){
    	return "login"; 
    	}

    @GetMapping("/register")
    public String register(){
    	return "register"; 
    	}

    @PostMapping("/register")
    public String save(User u){
        service.register(u);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(String email,String password,HttpSession s){

        User u=service.login(email,password);

        if(u!=null){
            s.setAttribute("user",u);
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession s){
        s.invalidate();
        return "redirect:/";
    }
}