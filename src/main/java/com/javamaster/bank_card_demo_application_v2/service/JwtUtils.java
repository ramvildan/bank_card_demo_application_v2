package com.javamaster.bank_card_demo_application_v2.service;

import com.javamaster.bank_card_demo_application_v2.domain.JwtAuthentication;
import com.javamaster.bank_card_demo_application_v2.entity.type.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {

        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setName(claims.get("Name", String.class));
        jwtInfoToken.setUsername(claims.getSubject());

        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {

        List<String> roles = List.of(claims.get("roles", String.class));

        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
