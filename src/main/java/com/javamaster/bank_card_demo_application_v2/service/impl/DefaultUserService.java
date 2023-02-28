package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.PaymentCardConverter;
import com.javamaster.bank_card_demo_application_v2.converter.UserConverter;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.dto.PhoneNumberResponseDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    public UserDto createUser(@Valid UserCreateDto userCreateDto) {

        User newUser = User.builder()
                .name(userCreateDto.getName())
                .surname(userCreateDto.getSurname())
                .patronymic(userCreateDto.getPatronymic())
                .phoneNumber(userCreateDto.getPhoneNumber())
                .email(userCreateDto.getEmail())
                .build();

        return userConverter.fromUserToUserDto(
                userRepository.save(newUser));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.findUserById(userId).setDeleted(true);
    }

    @Override
    public void updateUserStatus(Integer userId, @Valid PaymentCardCreateDto paymentCardCreateDto) {

        User userWithStatus = userRepository.findUserById(userId);
        userWithStatus.setStatus(paymentCardCreateDto.getCardType());
        userRepository.save(userWithStatus);
    }

    @Override
    public PhoneNumberResponseDto getPageByStatusAndCurrencyType(CardType status, CurrencyType type, Pageable pageable) {
        Page<User> currentPage = userRepository.findAllByStatusAndCurrencyType(status, type, pageable);
        log.info("getPageByStatusAndCurrencyType: currentPage = {}", currentPage);
        return new PhoneNumberResponseDto(currentPage.stream()
                .map(User::getPhoneNumber)
                .collect(Collectors.toList()),
                currentPage.getSize(),
                currentPage.getTotalPages(),
                currentPage.getNumber());
    }
}
