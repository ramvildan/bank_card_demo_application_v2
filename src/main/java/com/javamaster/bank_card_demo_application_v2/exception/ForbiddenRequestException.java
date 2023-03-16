package com.javamaster.bank_card_demo_application_v2.exception;

public class ForbiddenRequestException extends RuntimeException {

    public ForbiddenRequestException(String message) {
        super(message);
    }
}
