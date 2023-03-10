package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.AppUserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.AppUserDto;
import com.javamaster.bank_card_demo_application_v2.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app_user")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "AppUser", description = "All methods to work with app users")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    @Operation(summary = "Registration new app user")
    public AppUserDto registrationNewAppUser(@RequestBody
                                                @Valid AppUserCreateDto appUserCreateDto) {

        log.info("registrationNewAppUser: AppUserCreateDto = {}", appUserCreateDto);

        return appUserService.createAppUser(appUserCreateDto);
    }
}
