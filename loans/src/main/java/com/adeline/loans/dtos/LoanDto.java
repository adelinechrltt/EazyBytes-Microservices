package com.adeline.loans.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoanDto {
    private Long loanId;
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Long totalLoan;
    private Long amountPaid;
    private Long outstandingAmount;
}
