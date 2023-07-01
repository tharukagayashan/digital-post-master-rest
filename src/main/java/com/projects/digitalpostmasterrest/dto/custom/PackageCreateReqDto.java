package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageCreateReqDto {
    private String sender;
    private String receiver;
    private Float length;
    private Float width;
    private Float height;
    private Float weight;
    private String instructions;
}