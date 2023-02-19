package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import jakarta.validation.Valid;

public interface PaymentCardService {

    PaymentCardDto createPaymentCard(Integer userId, @Valid PaymentCardCreateDto paymentCardCreateDto);
}
