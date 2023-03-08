package com.javamaster.bank_card_demo_application_v2.converter;

import com.javamaster.bank_card_demo_application_v2.dto.AppUserDto;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class AppUserConverter {

    public AppUserDto fromAppUserToAppUserDto (AppUser appUser) {

        if(isNull(appUser)) {
            return null;
        }
        return AppUserDto.builder()
                .id(appUser.getId())
                .login(appUser.getLogin())
                .name(appUser.getName())
                .surname(appUser.getSurname())
                .email(appUser.getEmail())
                .password(appUser.getPassword())
                .role(appUser.getRole())
                .build();
    }
}
