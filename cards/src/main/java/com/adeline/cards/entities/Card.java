package com.adeline.cards.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card extends BaseEntity {

    @Column(name="card_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="card_number")
    private Long cardNumber;

    @Column(name="card_type")
    private String cardType;

    @Column(name="total_limit")
    private Long totalLimit;

    @Column(name="amount_used")
    private Long amountUsed;

    @Column(name="available_amount")
    private Long availableAmount;
}
