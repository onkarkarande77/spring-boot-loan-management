package com.loanproject.loan_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.loanproject.loan_app.bean.Loan;
import com.loanproject.loan_app.bean.User;
import com.loanproject.loan_app.services.LoanService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoanController {

    @Autowired
    LoanService service;

    @GetMapping("/dashboard")
    public String dash(HttpSession s,ModelMap m){

        User u=(User)s.getAttribute("user");

        if(u==null) return "redirect:/login";

        Loan l=service.get(u);

        m.addAttribute("loan",l);
        return "dashboard";
    }

    @GetMapping("/applyLoan")
    public String form(){ return "applyLoan"; }

    @PostMapping("/applyLoan")
    public String apply(HttpSession s,double amount,double interest,int months){

        User u=(User)s.getAttribute("user");
        service.apply(u,amount,interest,months);

        return "redirect:/dashboard";
    }

    @GetMapping("/payPage")
    public String payPage(HttpSession s,ModelMap m){

        User u=(User)s.getAttribute("user");
        Loan l=service.get(u);

        m.addAttribute("loan",l);
        return "pay";
    }

    @PostMapping("/pay")
    public String pay(Long loanId,double amount){
        service.pay(loanId,amount);
        return "redirect:/dashboard";
    }
}
