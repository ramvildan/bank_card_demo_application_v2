package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.dto.UserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto createUser(@Valid UserCreateDto userCreateDto);

    void deleteUser(Integer userId);

}
