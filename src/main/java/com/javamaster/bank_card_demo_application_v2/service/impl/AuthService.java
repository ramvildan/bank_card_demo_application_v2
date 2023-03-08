package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserService appUserService;

    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;

    public JwtResponse login(@NotNull JwtRequest authRequest) throws AuthException {

        final AppUser appUser = appUserService.getByLogin(authRequest.getLogin())
                .orElseThrow(()-> new AuthException("AppUser not found"));
        if (appUser.getPassword().equals(authRequest.getPassword())) {

            final String accessToken = jwtProvider.generateAccessToken(appUser);
            final String refreshToken = jwtProvider.generateRefreshToken(appUser);
            refreshStorage.put(appUser.getLogin(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public JwtResponse getAccessToken(@NotNull String refreshToken) throws AuthException {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                final AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AuthException("AppUser not found"));
                final String accessToken = jwtProvider.generateAccessToken(appUser);

                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse getRefreshToken(@NotNull String refreshToken) throws AuthException {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                final AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AuthException("AppUser not found"));
                final String accessToken = jwtProvider.generateAccessToken(appUser);
                final String newRefreshToken = jwtProvider.generateRefreshToken(appUser);
                refreshStorage.put(appUser.getLogin(), newRefreshToken);

                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }
}
