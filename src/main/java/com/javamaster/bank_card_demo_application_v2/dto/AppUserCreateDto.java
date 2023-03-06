package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.Role;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Create app user Dto")
public class AppUserCreateDto {

    @Schema(description = "AppUser name")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Name must be specified")
    private String name;

    @Schema(description = "AppUser surname")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Surname must be specified")
    private String surname;

    @Schema(description = "AppUser email")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Email must be specified")
    private String email;

    @Schema(description = "AppUser password")
    @Size(min = 1, max = 255)
    @NotBlank(message = "Password must be specified")
    private String password;

    @Schema(description = "AppUser role")
    private Role role;
}
