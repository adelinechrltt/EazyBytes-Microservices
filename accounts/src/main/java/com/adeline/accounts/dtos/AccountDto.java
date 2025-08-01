package com.adeline.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {

    @Schema(
            description = "Account number",
            example = "3454433243"
    )
    @NotEmpty(message="AccountNumber cannot be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message="AccountNumber must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type"
    )
    @NotEmpty(message="AccountType cannot be a null or empty")
    private String accountType;

    @Schema(
            description = "Account branch address",
            example = "123 New York"
    )
    @NotEmpty(message="BranchAddress cannot be a null or empty")
    private String branchAddress;
}
