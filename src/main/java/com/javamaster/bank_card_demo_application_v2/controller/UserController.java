package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "User", description = "All methods for working with user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save new user")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        log.info("saveUser: userDto = {}", userDto);
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by user Id")
    public void deleteUser(@Parameter(description = "User Id")
                               @PathVariable Integer userId) {
        log.info("deleteUser: userId = {}", userId);
        userService.deleteUser(userId);
    }
}
