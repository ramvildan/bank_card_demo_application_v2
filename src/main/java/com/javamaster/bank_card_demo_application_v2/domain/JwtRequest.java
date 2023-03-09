package com.javamaster.bank_card_demo_application_v2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "Login")
    @NotNull(message = "Login must be specified")
    private String login;

    @Schema(description = "Password")
    @NotNull(message = "Password must be specified")
    private String password;

}
