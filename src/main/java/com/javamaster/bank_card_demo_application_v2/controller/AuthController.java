package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import com.javamaster.bank_card_demo_application_v2.domain.RefreshJwtRequest;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserDto;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import com.javamaster.bank_card_demo_application_v2.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Authentication", description = "All methods for authentication")
public class AuthController {

    private final AuthService authService;

    private final AppUserService appUserService;

    @PostMapping("login")
    @Operation(summary = "Login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest loginRequest) throws AuthException {

        final JwtResponse token = authService.login(loginRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    @Operation(summary = "New access token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {

        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    @Operation(summary = "New refresh token")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {

        final JwtResponse token = authService.getRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    @Operation(summary = "Registration new app user")
    public AppUserDto registrationNewAppUser(@RequestBody
                                             @Valid AppUserCreateDto appUserCreateDto) {

        log.info("registrationNewAppUser: AppUserCreateDto = {}", appUserCreateDto);

        return appUserService.createAppUser(appUserCreateDto);
    }


}
