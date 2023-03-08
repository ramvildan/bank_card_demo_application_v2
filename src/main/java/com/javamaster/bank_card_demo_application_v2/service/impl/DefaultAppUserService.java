package com.javamaster.bank_card_demo_application_v2.service.impl;

import com.javamaster.bank_card_demo_application_v2.converter.AppUserConverter;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserDto;
import com.javamaster.bank_card_demo_application_v2.entity.AppUser;
import com.javamaster.bank_card_demo_application_v2.repository.AppUserRepository;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultAppUserService implements AppUserService {

    private final List<AppUser> appUsers;

    private final AppUserRepository appUserRepository;

    private final AppUserConverter appUserConverter;

    @Override
    public AppUserDto createAppUser(@Valid AppUserCreateDto appUserCreateDto) {

        AppUser appUser = AppUser.builder()
                .login(appUserCreateDto.getLogin())
                .name(appUserCreateDto.getName())
                .surname(appUserCreateDto.getSurname())
                .email(appUserCreateDto.getEmail())
                .password(appUserCreateDto.getPassword())
                .role(appUserCreateDto.getRole())
                .build();

        return appUserConverter.fromAppUserToAppUserDto(
                appUserRepository.save(appUser));
    }

    @Override
    public Optional<AppUser> getByLogin(String login) {

        return appUsers.stream()
                .filter(appUser -> login.equals(appUser.getLogin()))
                .findFirst();
    }
}
