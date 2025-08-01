package com.adeline.cards.services.implementations;

import com.adeline.cards.dtos.CardDto;
import com.adeline.cards.entities.Card;
import com.adeline.cards.exceptions.CardAlreadyExistsException;
import com.adeline.cards.exceptions.ResourceNotFoundException;
import com.adeline.cards.mappers.CardMapper;
import com.adeline.cards.repositories.CardRepository;
import com.adeline.cards.services.ICardsService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardRepository cardRepository;

    @Override
    public void createCard(CardDto cardDto) {
        Card card = CardMapper.mapToCard(new Card(), cardDto);

        Optional<Card> optionalCard = cardRepository.findByMobileNumber(cardDto.getMobileNumber());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with existing mobile number" + cardDto.getMobileNumber());
        }

        card.setCreatedAt(LocalDateTime.now());
        Card savedCard = cardRepository.save(card);
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cannot find card by the specified mobile number")
        );
        CardDto cardDto = CardMapper.mapToCardDto(card, new CardDto());
        return cardDto;
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        boolean isUpdated = false;

        Card card = cardRepository.findByMobileNumber(cardDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Cannot find card by the specified mobile number")
        );
        CardMapper.mapToCard(card,cardDto);
        card = cardRepository.save(card);

        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cannot find card by the specified mobile number")
        );
        cardRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
