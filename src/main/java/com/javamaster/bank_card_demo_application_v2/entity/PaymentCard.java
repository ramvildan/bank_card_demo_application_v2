package com.javamaster.bank_card_demo_application_v2.entity;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "card_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_number", nullable = false)
    private Integer cardNumber;

    @Column(name = "currency_type", nullable = false)
    private CurrencyType currencyType;

    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private Integer userId;
}
