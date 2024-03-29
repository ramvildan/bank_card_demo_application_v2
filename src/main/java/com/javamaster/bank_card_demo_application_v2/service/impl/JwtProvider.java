package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Log4j2
@Component
public class JwtProvider {

    private final SecretKey jwtAccessSecret;

    private final SecretKey jwtRefreshSecret;

    public JwtProvider(@Value("${jwt.secret.access}") String jwtAccessSecret,
                       @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {

        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(@NotNull AppUser appUser) {

        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setSubject(appUser.getLogin())
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("roles", appUser.getRole())
                .claim("name", appUser.getName())
                .claim("surname", appUser.getSurname())
                .compact();
    }

    public String generateRefreshToken(@NotNull AppUser appUser) {

        LocalDateTime now = LocalDateTime.now();
        Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .setSubject(appUser.getLogin())
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NotNull String accessToken) {

        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NotNull String refreshToken) {

        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(@NotNull String token, @NotNull Key secret) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException expEx) {

            log.error("Token expired", expEx);

        } catch (UnsupportedJwtException unsEx) {

            log.error("Unsupported jwt", unsEx);

        } catch (MalformedJwtException mjEx) {

            log.error("Malformed jwt", mjEx);

        } catch (SignatureException sEx) {

            log.error("Invalid signature", sEx);

        } catch (Exception e) {

            log.error("Invalid token", e);

        }
        return false;
    }

    public Claims getAccessClaims(@NotNull String token) {

        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NotNull String token) {

        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@NotNull String token, @NotNull Key secret) {

        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
