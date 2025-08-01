package com.adeline.cards.services.implementations;

import com.adeline.cards.dtos.CardDto;
import com.adeline.cards.entities.Card;
import com.adeline.cards.exceptions.CardAlreadyExistsException;
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
        Card card = CardMapper.mapToCard(new Card(), cardDto);

        Optional<Card> optionalCard = cardRepository.findByCardId(cardDto.getCardId());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with existing card ID" + cardDto.getCardId());
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
