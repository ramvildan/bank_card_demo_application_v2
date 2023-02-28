package com.javamaster.bank_card_demo_application_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
