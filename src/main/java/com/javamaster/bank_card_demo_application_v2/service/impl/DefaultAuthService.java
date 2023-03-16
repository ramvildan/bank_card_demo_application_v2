package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import com.javamaster.bank_card_demo_application_v2.exception.AppUserNotFoundException;
import com.javamaster.bank_card_demo_application_v2.exception.WrongPasswordException;
import com.javamaster.bank_card_demo_application_v2.exception.WrongTokenException;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import com.javamaster.bank_card_demo_application_v2.service.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final AppUserService appUserService;

    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(@NotNull JwtRequest loginRequest) {

        AppUser appUser = appUserService.getByLogin(loginRequest.getLogin())
                .orElseThrow(()-> new AppUserNotFoundException(loginRequest.getLogin()));
        if (passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {

            String accessToken = jwtProvider.generateAccessToken(appUser);
            String refreshToken = jwtProvider.generateRefreshToken(appUser);
            refreshStorage.put(appUser.getLogin(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NotNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AppUserNotFoundException(login));
                String accessToken = jwtProvider.generateAccessToken(appUser);

                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse getRefreshToken(@NotNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AppUserNotFoundException(login));
                String accessToken = jwtProvider.generateAccessToken(appUser);
                String newRefreshToken = jwtProvider.generateRefreshToken(appUser);
                refreshStorage.put(appUser.getLogin(), newRefreshToken);

                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new WrongTokenException("Invalid JWT token");
    }
}
