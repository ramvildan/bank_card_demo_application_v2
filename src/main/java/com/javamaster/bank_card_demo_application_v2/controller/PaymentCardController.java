package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment_card")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Payment card", description = "All methods for working with Payment card")
public class PaymentCardController {

    private final PaymentCardService paymentCardService;

    @PostMapping("/{userId}")
    public PaymentCardDto createPaymentCard(@PathVariable Integer userId,
                                            @RequestBody PaymentCardDto paymentCardDto) {
        log.info("createPaymentCard: userId = {}, PaymentCardDto = {}", userId, paymentCardDto);
        return paymentCardService.createPaymentCard(userId, paymentCardDto);
    }
}
