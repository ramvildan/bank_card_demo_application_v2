package com.javamaster.bank_card_demo_application_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends BadRequestException{

    public UserNotFoundException(Integer id) {
        super(String.format("User with this Id (%s) does not exist", id));
    }
}
