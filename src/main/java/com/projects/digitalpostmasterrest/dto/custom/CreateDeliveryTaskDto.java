package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateDeliveryTaskDto {
    @NotNull
    private String pickupAddress;
    @NotNull
    private String deliveryAddress;
    @NotNull
    private Integer packageId;
    @NotNull
    private Integer agentId;
}
