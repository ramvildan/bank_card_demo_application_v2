package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    @Override
    public UserDto saveUser(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
