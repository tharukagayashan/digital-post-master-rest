package com.projects.digitalpostmasterrest.dto.custom;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageCreateReqDto {

    @NotNull
    private String sender;
    @NotNull
    private String receiver;
    @NotNull
    private String receiverAddress;
    @NotNull
    private Float length;
    @NotNull
    private Float width;
    @NotNull
    private Float height;
    @NotNull
    private Float weight;
    private String instructions;
}