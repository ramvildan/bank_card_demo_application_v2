package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotBlank(message = "Name must be specified")
    private String name;

    @NotBlank(message = "Surname must be specified")
    private String surname;

    @NotBlank(message = "Patronymic must be specified")
    private String patronymic;

    @Email(message = "E-mail wrong format")
    private String email;

    private PaymentCardDto paymentCardDto;

    private CardType status;

}
