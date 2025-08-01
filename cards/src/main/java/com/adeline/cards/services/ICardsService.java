package com.adeline.cards.services;

import com.adeline.cards.dtos.CardDto;

public interface ICardsService {
    void createCard(CardDto cardDto);
    CardDto fetchCard(String mobileNumber);
    boolean updateCard(CardDto customerDto);
    boolean deleteCard(String mobileNumber);
}
