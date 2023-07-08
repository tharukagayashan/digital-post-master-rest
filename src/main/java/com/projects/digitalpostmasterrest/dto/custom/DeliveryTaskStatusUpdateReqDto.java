package com.projects.digitalpostmasterrest.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryTaskStatusUpdateReqDto {

    @NotNull
    private Integer deliveryTaskId;
    @NotNull
    @NotEmpty
    private String status;
}
