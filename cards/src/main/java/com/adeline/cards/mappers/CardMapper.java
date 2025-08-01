package com.adeline.cards.mappers;

import com.adeline.cards.dtos.CardDto;
import com.adeline.cards.entities.Card;

public class CardMapper {
    public static CardDto mapToCardDto(Card card, CardDto cardDto) {
        cardDto.setCardId(card.getCardId());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setMobileNumber(card.getMobileNumber());
        return cardDto;
    }

    public static Card mapToCard(Card card, CardDto cardDto) {
        card.setCardId(cardDto.getCardId());
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNumber(cardDto.getMobileNumber());
        return card;
    }
}
