package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.CardType;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "User ID")
    private Integer id;

    @Schema(description = "User name")
    private String name;

    @Schema(description = "User surname")
    private String surname;

    @Schema(description = "User patronymic")
    private String patronymic;

    @Schema(description = "User phone number")
    private String phoneNumber;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "User status")
    private CardType status;

}
