package com.javamaster.bank_card_demo_application_v2.dto;

import com.javamaster.bank_card_demo_application_v2.entity.type.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "App user information")
public class AppUserDto {

    @Schema(description = "AppUser ID")
    private Integer id;

    @Schema(description = "AppUser name")
    private String name;

    @Schema(description = "AppUser surname")
    private String surname;

    @Schema(description = "AppUser email")
    private String email;

    @Schema(description = "AppUser password")
    private String password;

    @Schema(description = "AppUser role")
    private Role role;
}
