package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Create payment card Dto")
public class PaymentCardCreateDto {

    @Schema(description = "Card number")
    @Size(min = 1, max = 20)
    @NotNull(message = "Card number must be specified")
    private Integer cardNumber;

    @NotNull
    private CardType cardType;

    @NotNull
    private CurrencyType currencyType;
}
