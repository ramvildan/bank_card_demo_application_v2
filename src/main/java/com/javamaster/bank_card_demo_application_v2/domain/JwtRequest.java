package com.javamaster.bank_card_demo_application_v2.domain;

import lombok.Data;

@Data
public class JwtRequest {

    private String login;
    private String password;

}
