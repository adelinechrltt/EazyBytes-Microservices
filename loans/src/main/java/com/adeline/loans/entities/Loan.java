package com.adeline.loans.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Loan extends BaseEntity {

    @Column(name="loan_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="loan_number")
    private String loanNumber;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private long totalLoan;

    @Column(name="amount_paid")
    private long amountPaid;

    @Column(name="outstanding_amount")
    private Long outstandingAmount;
}
