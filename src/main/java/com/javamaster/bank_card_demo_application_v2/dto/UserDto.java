package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "User information")
public class UserDto {

    @Schema(description = "User Id")
    @Min(1)
    @Max(99999999)
    private Integer id;

    @Schema(description = "Name")
    @Size(min = 1, max = 15)
    @NotBlank(message = "Name must be specified")
    private String name;

    @Schema(description = "Surname")
    @Size(min = 1, max = 15)
    @NotBlank(message = "Surname must be specified")
    private String surname;

    @Schema(description = "Patronymic")
    @Size(min = 1, max = 15)
    @NotBlank(message = "Patronymic must be specified")
    private String patronymic;

    @Schema(description = "Phone number")
    @Size(min = 1, max = 15)
    @NotNull(message = "Phone number must be specified")
    private Integer phoneNumber;

    @Schema(description = "E-mail")
    @Email(message = "E-mail wrong format")
    private String email;

    private PaymentCardDto paymentCardDto;

    private CardType status;

}
