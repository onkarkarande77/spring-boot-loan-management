package com.loanproject.loan_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanproject.loan_app.bean.Loan;
import com.loanproject.loan_app.bean.User;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	Loan findByUser(User user);
}
