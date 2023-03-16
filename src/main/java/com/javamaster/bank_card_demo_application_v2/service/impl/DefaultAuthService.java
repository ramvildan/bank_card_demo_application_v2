package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.domain.JwtRequest;
import com.javamaster.bank_card_demo_application_v2.domain.JwtResponse;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import com.javamaster.bank_card_demo_application_v2.service.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
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
    public JwtResponse login(@NotNull JwtRequest loginRequest) throws AuthException {

        AppUser appUser = appUserService.getByLogin(loginRequest.getLogin())
                .orElseThrow(()-> new AuthException("AppUser not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {

            String accessToken = jwtProvider.generateAccessToken(appUser);
            String refreshToken = jwtProvider.generateRefreshToken(appUser);
            refreshStorage.put(appUser.getLogin(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NotNull String refreshToken) throws AuthException {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AuthException("AppUser not found"));
                String accessToken = jwtProvider.generateAccessToken(appUser);

                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse getRefreshToken(@NotNull String refreshToken) throws AuthException {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AppUser appUser = appUserService.getByLogin(login)
                        .orElseThrow(()-> new AuthException("AppUser not found"));
                String accessToken = jwtProvider.generateAccessToken(appUser);
                String newRefreshToken = jwtProvider.generateRefreshToken(appUser);
                refreshStorage.put(appUser.getLogin(), newRefreshToken);

                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }
}
