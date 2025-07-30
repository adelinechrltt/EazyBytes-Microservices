package com.adeline.accounts.controllers;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.dtos.ResponseDto;
import com.adeline.accounts.services.IAccountsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

///  controller layer is only responsible for accepting and sending requests
///  business logic is done in the service layer

@RestController
@RequestMapping(path="/api", produces=(MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
public class AccountsController {

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
}
