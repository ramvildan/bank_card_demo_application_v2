package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.dto.AppUserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserDto;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAppUserService implements AppUserService {

    @Override
    public AppUserDto createAppUser(@Valid AppUserCreateDto appUserCreateDto) {

        AppUser appUser = AppUser.builder()
                .name(appUserCreateDto.getName())
                .surname(appUserCreateDto.getSurname())
                .email(appUserCreateDto.getEmail())
                .password(appUserCreateDto.getPassword())
                .role(appUserCreateDto.getRole())
                .build();

        return null;
    }
}
