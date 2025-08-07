package com.adeline.accounts.controllers;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.dtos.ErrorResponseDto;
import com.adeline.accounts.dtos.ResponseDto;
import com.adeline.accounts.services.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

///  controller layer is only responsible for accepting and sending requests
///  business logic is done in the service layer

@Tag(
        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, FETCH, UPDATE, and DELETE account details"
)
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

    @Value("${build.version}")
    private String buildVersion;

//    @GetMapping("/sayHello")
//    public String sayHello() {
//        return "Hello World!";
//    }

    @Operation(
            summary = "Create Account",
            description = "REST API endpoint to create new Customer & Account inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Accounts",
            description = "REST API endpoint to fetch Customer & Account data based on mobile number inside EazyBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
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

    @Operation(
            summary = "Update Account Details",
            description = "REST API endpoint to update Customer & Account data based on account number inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
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

    @Operation(
            summary = "Delete Account",
            description = "REST API endpoint to delete Customer & Account data based on mobile number inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
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

    @Operation(
            summary = "Get Build Information",
            description = "Get build information deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error"
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
}
