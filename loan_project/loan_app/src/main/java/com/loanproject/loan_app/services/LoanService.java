package com.loanproject.loan_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanproject.loan_app.bean.Loan;
import com.loanproject.loan_app.bean.User;
import com.loanproject.loan_app.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    LoanRepository repo;

    public Loan apply(User user,double amount,double interest,int months){

        double r = interest/(12*100);

        double emi = (amount*r*Math.pow(1+r,months))/
                     (Math.pow(1+r,months)-1);

        Loan l=new Loan();
        l.setUser(user);
        l.setAmount(amount);
        l.setInterest(interest);
        l.setMonths(months);
        l.setEmi(emi);
        l.setRemaining(amount);

        return repo.save(l);
    }

    public Loan get(User user){
        return repo.findByUser(user);
    }

    public void pay(Long id,double amt){
        Loan l=repo.findById(id).get();
        l.setRemaining(l.getRemaining()-amt);
        repo.save(l);
    }
}
