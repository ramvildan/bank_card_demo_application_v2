package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.PhoneNumberResponseDto;
import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import com.javamaster.bank_card_demo_application_v2.service.PageResponseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get_phone_numbers")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "PhoneNumberGetController", description = "Controller to get phone numbers list")
public class PhoneNumberGetController {

    private final PageResponseService pageResponseService;

    @GetMapping
    public PhoneNumberResponseDto getPageByStatusAndCurrencyType(@RequestParam @NotNull CardType status,
                                                                 @RequestParam @NotNull CurrencyType currencyType,
                                                                 @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                                 @RequestParam(required = false, defaultValue = "0") int pageNumber){

        log.info("getPageByStatusAndCurrencyType: status = {}, currencyType = {}, pageSize = {}, pageNumber = {}",
                status, currencyType, pageSize, pageNumber);

        return pageResponseService.getPageByStatusAndCurrencyType(status, currencyType, PageRequest.of(pageNumber, pageSize));
    }
}
