package com.javamaster.bank_card_demo_application_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AppUserNotFoundException extends ForbiddenRequestException {

        public AppUserNotFoundException(String login) {
            super(String.format("App user (%s) does not exist", login));
        }
}
