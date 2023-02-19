package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.UserConverter;
import com.javamaster.bank_card_demo_application_v2.dto.CreateUserDto;
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
    public UserDto createUser(@Valid CreateUserDto createUserDto) {
        UserDto userDto = UserDto.builder()
                .id(createUserDto.getId())
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .patronymic(createUserDto.getPatronymic())
                .phoneNumber(createUserDto.getPhoneNumber())
                .email(createUserDto.getEmail())
                .build();
        User savedUser = userRepository.save(userConverter.fromUserDtoToUser(userDto));
        return userConverter.fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
