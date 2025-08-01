package com.adeline.loans.services.impl;

import com.adeline.loans.dtos.LoanDto;
import com.adeline.loans.entities.Loan;
import com.adeline.loans.exceptions.LoanAlreadyExistsException;
import com.adeline.loans.exceptions.ResourceNotFoundException;
import com.adeline.loans.mappers.LoanMapper;
import com.adeline.loans.repositories.LoanRepository;
import com.adeline.loans.services.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(LoanDto loanDto) {
        Loan loan = LoanMapper.mapToLoan(loanDto, new Loan());

        Optional<Loan> optionalLoan =  loanRepository.findByMobileNumber(loanDto.getMobileNumber());
        if (optionalLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists");
        }

        loan.setCreatedAt(LocalDateTime.now());
        loanRepository.save(loan);
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan not found with mobile number: " + mobileNumber)
        );

        LoanDto loanDto = LoanMapper.mapToLoanDto(loan, new LoanDto());
        return loanDto;
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        boolean isUpdated = false;

        Loan loan = loanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan not found with mobile number: " + loanDto.getMobileNumber())
        );

        LoanMapper.mapToLoan(loanDto,loan);
        loanRepository.save(loan);
        isUpdated = true;

        return isUpdated;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted = false;
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan not found with mobile number: " + mobileNumber)
        );

        loanRepository.delete(loan);
        isDeleted = true;
        return isDeleted;
    }
}
