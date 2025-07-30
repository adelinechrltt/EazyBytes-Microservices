package com.adeline.accounts.services.impl;

import com.adeline.accounts.constants.AccountConstants;
import com.adeline.accounts.dtos.AccountDto;
import com.adeline.accounts.dtos.CustomerDto;
import com.adeline.accounts.entities.Account;
import com.adeline.accounts.entities.Customer;
import com.adeline.accounts.exceptions.CustomerAlreadyExistsException;
import com.adeline.accounts.exceptions.ResourceNotFoundException;
import com.adeline.accounts.mapper.AccountsMapper;
import com.adeline.accounts.mapper.CustomerMapper;
import com.adeline.accounts.repositories.AccountRepository;
import com.adeline.accounts.repositories.CustomerRepository;
import com.adeline.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with existing phone number" + customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        Customer savedCustomer = customerRepository.save(customer); /// ---> boilerplate code for connecting with the SQL etc. abstracted by Spring JPA framework
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer ID", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountsMapper.mapToAccountsDto(account, new AccountDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;
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
