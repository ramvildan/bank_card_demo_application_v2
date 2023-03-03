package com.javamaster.bank_card_demo_application_v2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(description = "Create user Dto")
public class UserCreateDto {

    @Schema(description = "User name")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Name must be specified")
    private String name;

    @Schema(description = "User surname")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Surname must be specified")
    private String surname;

    @Schema(description = "User patronymic")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Patronymic must be specified")
    private String patronymic;

    @Schema(description = "User phone number")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Phone number must be specified")
    private String phoneNumber;

    @Schema(description = "User email")
    @Email(message = "E-mail wrong format")
    @Size(min = 1, max = 255)
    private String email;

}
