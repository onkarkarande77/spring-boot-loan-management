package com.loanproject.loan_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanproject.loan_app.bean.User;
import com.loanproject.loan_app.repository.UserRepository;

@Service
public class UserService{

    @Autowired
    UserRepository repo;

    public void register(User u){
        repo.save(u);
    }

    public User login(String e,String p){
        return repo.findByEmailAndPassword(e,p);
    }
}