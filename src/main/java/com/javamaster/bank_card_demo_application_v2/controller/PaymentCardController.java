package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment_card")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Payment card", description = "All methods to work with payment card")
public class PaymentCardController {

    private final PaymentCardService paymentCardService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{userId}")
    @Operation(summary = "Creating payment card for user")
    public ResponseEntity<PaymentCardDto> createPaymentCard(@PathVariable Integer userId,
                                                            @RequestBody @Valid PaymentCardCreateDto paymentCardCreateDto) {
        log.info("createPaymentCard: userId = {}, createPaymentCardDto = {}", userId, paymentCardCreateDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(paymentCardService.createPaymentCard(userId, paymentCardCreateDto));
    }
}
