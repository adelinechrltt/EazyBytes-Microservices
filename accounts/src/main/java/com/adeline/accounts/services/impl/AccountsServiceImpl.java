package com.adeline.accounts.services.impl;

import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.repositories.AccountRepository;
import com.adeline.accounts.repositories.CustomerRepository;
import com.adeline.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
