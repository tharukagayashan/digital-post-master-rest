package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageCreateReqDto {

    @NotNull
    private Integer userId;
    @NotNull
    private String receiver;
    @NotNull
    private String receiverAddress;
    @DecimalMin(value = "0")
    private Float length;
    @DecimalMin(value = "0")
    private Float width;
    @DecimalMin(value = "0")
    private Float height;
    @DecimalMin(value = "")
    private Float weight;
    private String instructions;
}