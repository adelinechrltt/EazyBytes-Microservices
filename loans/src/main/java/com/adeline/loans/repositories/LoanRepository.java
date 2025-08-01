package com.adeline.loans.repositories;

import com.adeline.loans.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);
    Optional<Loan> findByLoanNumber(String loanNumber);
}
