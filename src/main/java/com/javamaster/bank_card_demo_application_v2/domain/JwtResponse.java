package com.javamaster.bank_card_demo_application_v2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Token responses")
public class JwtResponse {

    @Schema(description = "Bearer")
    private final String type = "Bearer";

    @Schema(description = "Access token")
    private String accessToken;

    @Schema(description = "Refresh token")
    private String refreshToken;

}
