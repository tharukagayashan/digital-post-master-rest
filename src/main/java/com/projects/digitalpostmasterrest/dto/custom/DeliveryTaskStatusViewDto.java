package com.projects.digitalpostmasterrest.dto.custom;

import com.projects.digitalpostmasterrest.dto.DeliveryTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryTaskStatusViewDto {
    private Integer count;
    private String status;
    private List<DeliveryTaskDto> taskList;
}