package com.adeline.cards.services.implementations;

import com.adeline.cards.dtos.CardDto;
import com.adeline.cards.mappers.CardMapper;
import com.adeline.cards.repositories.CardRepository;
import com.adeline.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardRepository cardRepository;

    @Override
    public void createCard(CardDto cardDto) {
        Card card = CardMapper

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with existing phone number" + customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customer); /// ---> boilerplate code for connecting with the SQL etc. abstracted by Spring JPA framework
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateCard(CardDto customerDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}
