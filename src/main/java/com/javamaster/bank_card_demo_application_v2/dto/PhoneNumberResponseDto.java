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

    private List<Integer> phoneNumbers;
    private int size;
    private int count;
    private int page;
}
