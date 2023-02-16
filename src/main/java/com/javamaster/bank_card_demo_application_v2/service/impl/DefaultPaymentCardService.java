package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.PaymentCardConverter;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.entity.PaymentCard;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.repository.PaymentCardRepository;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPaymentCardService implements PaymentCardService {

    private final UserRepository userRepository;

    private final PaymentCardRepository paymentCardRepository;

    private final PaymentCardConverter paymentCardConverter;

    @Override
    public PaymentCardDto createPaymentCard(Integer userId, @Valid PaymentCardDto paymentCardDto) {
        User userWithAddedCard = userRepository.findUserById(userId);
        PaymentCard newPaymentCard = PaymentCard.builder()
                .id(paymentCardDto.getId())
                .cardNumber(paymentCardDto.getCardNumber())
                .cardType(paymentCardDto.getCardType())
                .currencyType(paymentCardDto.getCurrencyType())
                .user(userWithAddedCard)
                .build();
        return paymentCardConverter.fromPaymentCardToPaymentCardDto(
                paymentCardRepository.save(newPaymentCard)
        );
    }
}
