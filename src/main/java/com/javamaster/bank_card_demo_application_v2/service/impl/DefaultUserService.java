package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.UserConverter;
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

import java.util.Date;
import java.util.function.Function;
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
                .createdAt(new Date())
                .build();

        return userConverter.fromUserToUserDto(
                userRepository.save(newUser));
    }

    @Override
    public UserDto deleteUser(Integer userId) {

        User deletedUser = userRepository.findUserById(userId);
        deletedUser.setDeleted(true);
        deletedUser.setUpdatedAt(new Date());

        return userConverter.fromUserToUserDto(userRepository.save(deletedUser));
    }

    @Override
    public PhoneNumberResponseDto getPageByStatusAndCurrencyType(CardType status, CurrencyType type, Pageable pageable) {
        Page<User> currentPage = userRepository.findAllByStatusAndCurrencyType(status, type, pageable);

        log.info("getPageByStatusAndCurrencyType: currentPage = {}", currentPage);

        return
                PhoneNumberResponseDto.builder()
                .phoneNumbers(currentPage.stream().map(User::getPhoneNumber).collect(Collectors.toList()))
                .size(currentPage.getSize())
                .count(currentPage.getTotalPages())
                .page(currentPage.getNumber())
                .build();
    }
}
