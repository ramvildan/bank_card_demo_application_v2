package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.PaymentCardConverter;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.entity.PaymentCard;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.exception.UserNotFoundException;
import com.javamaster.bank_card_demo_application_v2.repository.PaymentCardRepository;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DefaultPaymentCardService implements PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;

    private final UserRepository userRepository;

    private final PaymentCardConverter paymentCardConverter;

    @Override
    public PaymentCardDto createPaymentCard(Integer userId, @Valid PaymentCardCreateDto paymentCardCreateDto) {

        PaymentCard newPaymentCard = PaymentCard.builder()
                .cardNumber(paymentCardCreateDto.getCardNumber())
                .cardType(paymentCardCreateDto.getCardType())
                .currencyType(paymentCardCreateDto.getCurrencyType())
                .createdAt(new Date())
                .updatedAt(new Date())
                .userId(userId)
                .build();

        User userWithStatus = userRepository
                .findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));

        userWithStatus.setStatus(newPaymentCard.getCardType());
        userWithStatus.setCurrencyType(newPaymentCard.getCurrencyType());
        userWithStatus.setUpdatedAt(new Date());
        userRepository.save(userWithStatus);

        return paymentCardConverter.fromPaymentCardToPaymentCardDto(
                paymentCardRepository.save(newPaymentCard)
        );
    }
}
