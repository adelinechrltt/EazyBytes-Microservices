package com.adeline.loans.controllers;

import com.adeline.loans.constants.LoansConstants;
import com.adeline.loans.dtos.LoanDto;
import com.adeline.loans.dtos.ResponseDto;
import com.adeline.loans.entities.Loan;
import com.adeline.loans.services.ILoanService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces=(MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
@Validated
public class LoanController {

    private final ILoanService iLoanService;
    private ILoanService loanService;

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoan(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number must be 10 digits")
            String mobileNumber
    ){
        LoanDto loanDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestBody @Validated
            LoanDto loanDto
    ) {
        iLoanService.createLoan(loanDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(
            @RequestBody @Validated
            LoanDto loanDto
    ) {
        boolean isUpdated = loanService.updateLoan(loanDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number must be 10 digits")
            String mobileNumber
    ) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
