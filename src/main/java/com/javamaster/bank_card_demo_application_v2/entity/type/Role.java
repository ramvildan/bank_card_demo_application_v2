package com.javamaster.bank_card_demo_application_v2.entity.type;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority{

    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private final String vale;
    @Override
    public String getAuthority() {
        return vale;
    }
}
