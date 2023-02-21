package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.dto.PhoneNumberResponseDto;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PageResponseService {

    PhoneNumberResponseDto getPageByStatusAndCurrencyType(CardType status, CurrencyType type, Pageable pageable);
}
