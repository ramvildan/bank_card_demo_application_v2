package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    JwtResponse login(@NotNull JwtRequest loginRequest);

    JwtResponse getAccessToken(@NotNull String refreshToken);

    JwtResponse getRefreshToken(@NotNull String refreshToken);
}
