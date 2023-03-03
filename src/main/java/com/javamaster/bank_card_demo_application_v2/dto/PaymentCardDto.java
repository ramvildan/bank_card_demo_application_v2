package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Payment card information")
public class PaymentCardDto {

    @Schema(description = "Payment card ID")
    private Integer id;

    @Schema(description = "Payment card number")
    private String cardNumber;

    @Schema(description = "Payment card type")
    private CardType cardType;

    @Schema(description = "Currency type")
    private CurrencyType currencyType;
}
