package com.adeline.accounts.services.impl;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.entities.Account;
import com.adeline.accounts.entities.Customer;
import com.adeline.accounts.mapper.CustomerMapper;
import com.adeline.accounts.repositories.AccountRepository;
import com.adeline.accounts.repositories.CustomerRepository;
import com.adeline.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer); /// ---> boilerplate code for connecting with the SQL etc. abstracted by Spring JPA framework

        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }
}
