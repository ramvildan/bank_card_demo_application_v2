package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.UserConverter;
import com.javamaster.bank_card_demo_application_v2.dto.UserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
        userRepository.deleteById(userId);
    }
}
