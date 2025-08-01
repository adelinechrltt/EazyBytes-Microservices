package com.adeline.loans.services;

import com.adeline.loans.dtos.LoanDto;

public interface ILoanService {

    void createLoan(LoanDto loanDto);
    LoanDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);
}
