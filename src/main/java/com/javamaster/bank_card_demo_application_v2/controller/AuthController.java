package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import com.javamaster.bank_card_demo_application_v2.domain.RefreshJwtRequest;
import com.javamaster.bank_card_demo_application_v2.service.impl.DefaultAuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DefaultAuthService defaultAuthService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest loginRequest) throws AuthException {

        final JwtResponse token = defaultAuthService.login(loginRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {

        final JwtResponse token = defaultAuthService.getAccessToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {

        final JwtResponse token = defaultAuthService.getRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }
}
