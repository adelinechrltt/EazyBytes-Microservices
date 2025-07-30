package com.adeline.accounts.controllers;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.dtos.ResponseDto;
import com.adeline.accounts.services.IAccountsService;
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
public class AccountsController {

    private final IAccountsService iAccountsService;
    /// dependency injection:
    /// i don't explicitly declare
    /// this.accountsService = new AccountsServiceImpl(...);
    /// because spring alreadu automatically wires it up for me
    private IAccountsService accountsService;

//    @GetMapping("/sayHello")
//    public String sayHello() {
//        return "Hello World!";
//    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }
}
