package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface PaymentCardService {

    PaymentCardDto createPaymentCard(Integer userId, @Valid PaymentCardCreateDto paymentCardCreateDto);
}
