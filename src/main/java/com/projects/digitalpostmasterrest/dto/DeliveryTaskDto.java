package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryTaskDto extends AuditModel {

    private Integer deliveryTaskId;
    private String pickupAddress;
    private String deliveryAddress;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer packageId;
    private Integer agentId;

    private PackageDetailDto packageDetail;
    private AgentDto agent;

}
