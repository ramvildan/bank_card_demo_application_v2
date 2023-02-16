package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPaymentCardService implements PaymentCardService {
    
    @Override
    public PaymentCardDto createPaymentCard(Integer userId, PaymentCardDto paymentCardDto) {
        return null;
    }
}
