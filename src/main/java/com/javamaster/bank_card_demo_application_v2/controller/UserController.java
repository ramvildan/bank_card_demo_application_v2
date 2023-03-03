package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.PhoneNumberResponseDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import com.javamaster.bank_card_demo_application_v2.exception.BadRequestException;
import com.javamaster.bank_card_demo_application_v2.exception.UserNotFoundException;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "User", description = "All methods to work with user")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping
    @Operation(summary = "Create new user")
    public UserDto createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        log.info("saveUser: createUserDto = {}", userCreateDto);
        return userService.createUser(userCreateDto);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by user Id")
    public void deleteUser(@Parameter(description = "User Id")
                               @PathVariable Integer userId) throws BadRequestException {
        log.info("deleteUser: userId = {}", userId);
        if (userRepository.existsById(userId)) {
            userService.deleteUser(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }


    @GetMapping
    @Operation(summary = "Get phone numbers")
    public PhoneNumberResponseDto getPageByStatusAndCurrencyType(@RequestParam @NotNull CardType status,
                                                                 @RequestParam @NotNull CurrencyType currencyType,
                                                                 @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                                 @RequestParam(required = false, defaultValue = "0") int pageNumber){

        log.info("getPageByStatusAndCurrencyType: status = {}, currencyType = {}, pageSize = {}, pageNumber = {}",
                status, currencyType, pageSize, pageNumber);

        return userService.getPageByStatusAndCurrencyType(status, currencyType, PageRequest.of(pageNumber, pageSize));
    }
}
