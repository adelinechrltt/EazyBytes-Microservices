package com.adeline.accounts.services;

import com.adeline.accounts.dtos.CustomerDto;

///  AccountsService interface naming convention starts with I
///  while AccountRepository interface doesn't
///  because the AccountsService interface will have a real class implementing it
///  while the repository interface will not

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
}
