package com.adeline.accounts.controllers;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.dtos.ResponseDto;
import com.adeline.accounts.services.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

///  controller layer is only responsible for accepting and sending requests
///  business logic is done in the service layer

@RestController
@RequestMapping(path="/api", produces=(MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
@Validated
public class AccountsController {

    /// dependency injection:
    /// i don't explicitly declare
    /// this.accountsService = new AccountsServiceImpl(...);
    /// because spring alreadu automatically wires it up for me
    private IAccountsService iAccountsService;

//    @GetMapping("/sayHello")
//    public String sayHello() {
//        return "Hello World!";
//    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number must be 10 digits")
            String mobileNumber
    ){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam @Pattern(regexp="(^$|[0-9]{10})", message="Mobile number must be 10 digits")
            String mobileNumber
    ){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
