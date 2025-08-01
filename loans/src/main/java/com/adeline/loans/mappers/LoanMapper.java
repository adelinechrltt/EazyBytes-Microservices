package com.adeline.loans.mappers;

import com.adeline.loans.dtos.LoanDto;
import com.adeline.loans.entities.Loan;

public class LoanMapper {
    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setLoanId(loan.getLoanId());
        loanDto.setLoanNumber(loan.getLoanNumber());
        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setLoanId(loanDto.getLoanId());
        loan.setLoanNumber(loanDto.getLoanNumber());
        return loan;
    }
}
