package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentCreateReqDto {
    private Float amount;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer packageId;
}
