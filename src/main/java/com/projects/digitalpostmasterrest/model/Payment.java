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
@Document(collection = "PAYMENT")
public class Payment extends AuditModel {
    @Id
    private String paymentId;
    @Field
    private String userId;
    @Field
    private String packageId;
    @Field
    private Float amount;
    @Field
    private String status;
    @Field
    private LocalDateTime time;
}
