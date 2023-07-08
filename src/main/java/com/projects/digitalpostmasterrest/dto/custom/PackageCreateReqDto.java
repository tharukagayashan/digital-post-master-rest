package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Float length;
    private Float width;
    private Float height;
    private Float weight;
    private String instructions;
}