package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCardDto {

    private Integer id;

    @NotBlank(message = "Card number must be specified")
    private Integer cardNumber;

    private CardType cardType;

    private CurrencyType currencyType;
}
