package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import com.javamaster.bank_card_demo_application_v2.entity.type.CurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Schema(description = "Payment card Id")
    @Min(1)
    @Max(99999999)
    private Integer id;

    @Schema(description = "Card number")
    @Size(min = 1, max = 20)
    @NotBlank(message = "Card number must be specified")
    private Integer cardNumber;

    private CardType cardType;

    private CurrencyType currencyType;
}
