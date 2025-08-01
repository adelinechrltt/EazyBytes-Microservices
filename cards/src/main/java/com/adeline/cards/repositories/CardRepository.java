package com.adeline.cards.repositories;

import com.adeline.cards.dtos.CardDto;
import com.adeline.cards.entities.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    /// for all methods in repository, we still use the property annotated as id in the entity class!
    /// we only do FK to PK conversion in the service layer

    Optional<Card> findByCardId(Long cardId);
    Optional<Card> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByCardId(Long cardId);
}
