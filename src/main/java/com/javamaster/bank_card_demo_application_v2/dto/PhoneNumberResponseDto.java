package com.javamaster.bank_card_demo_application_v2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Page response")
public class PhoneNumberResponseDto {

    @Schema(description = "Phone numbers list")
    private List<String> phoneNumbers;

    @Schema(description = "Size of page")
    private int size;

    @Schema(description = "Total number of pages")
    private int count;

    @Schema(description = "Number of page")
    private int page;
}
