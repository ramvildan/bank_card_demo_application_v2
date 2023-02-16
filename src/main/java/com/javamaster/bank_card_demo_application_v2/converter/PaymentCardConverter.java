package com.javamaster.bank_card_demo_application_v2.converter;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.entity.PaymentCard;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PaymentCardConverter {

    public PaymentCardDto fromPaymentCardToPaymentCardDto(PaymentCard paymentCard) {
        if(isNull(paymentCard)) {
            return null;
        }
        return PaymentCardDto.builder()
                .cardNumber(paymentCard.getCardNumber())
                .cardType(paymentCard.getCardType())
                .currencyType(paymentCard.getCurrencyType())
                .build();
    }
}
