package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.dto.PhoneNumberResponseDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.PageResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultPageResponseService implements PageResponseService {

    private final UserRepository userRepository;

    @Override
    public PhoneNumberResponseDto getPageByStatusAndCurrencyType(CardType status, CurrencyType type, Pageable pageable) {
        Page<User> currentPage = userRepository.findAllByStatusAndCurrencyType(status, type, pageable);
        return new PhoneNumberResponseDto(currentPage.stream()
                .map(User::getPhoneNumber)
                .collect(Collectors.toList()),
                currentPage.getSize(),
                currentPage.getTotalPages(),
                currentPage.getNumber());
    }
}
