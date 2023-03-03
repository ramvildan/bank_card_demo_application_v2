package com.javamaster.bank_card_demo_application_v2.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
