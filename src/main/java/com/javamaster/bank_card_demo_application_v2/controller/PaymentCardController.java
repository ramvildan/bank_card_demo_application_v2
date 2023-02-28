package com.javamaster.bank_card_demo_application_v2.controller;

import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardCreateDto;
import com.javamaster.bank_card_demo_application_v2.dto.PaymentCardDto;
import com.javamaster.bank_card_demo_application_v2.exception.BadRequestException;
import com.javamaster.bank_card_demo_application_v2.repository.UserRepository;
import com.javamaster.bank_card_demo_application_v2.service.PaymentCardService;
import com.javamaster.bank_card_demo_application_v2.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Payment card", description = "All methods to work with Payment card")
public class PaymentCardController {

    private final PaymentCardService paymentCardService;

    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/{userId}")
    public PaymentCardDto createPaymentCard(@PathVariable Integer userId,
                                            @RequestBody @Valid PaymentCardCreateDto paymentCardCreateDto) throws BadRequestException {
        log.info("createPaymentCard: userId = {}, createPaymentCardDto = {}", userId, paymentCardCreateDto);
        if (userRepository.existsById(userId)) {
            userService.deleteUser(userId);
        } else {
            throw  new BadRequestException(String.format("User with this Id (%s) does not exist", userId));
        }
        return paymentCardService.createPaymentCard(userId, paymentCardCreateDto);
    }
}
