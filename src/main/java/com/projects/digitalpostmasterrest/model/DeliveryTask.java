package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "DELIVERY_TASK")
public class DeliveryTask extends AuditModel {

    @Id
    private String deliveryTaskId;
    @Field
    private String packageId;
    @Field
    private String agentId;
    @Field
    private String pickupAddress;
    @Field
    private String deliveryAddress;
    @Field
    private String status;
    @Field
    private LocalDateTime startTime;
    @Field
    private LocalDateTime endTime;

}
