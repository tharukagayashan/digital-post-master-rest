package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryTaskDto extends AuditModel {

    private String deliveryTaskId;
    private String packageId;
    private String agentId;
    private String pickupAddress;
    private String deliveryAddress;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
