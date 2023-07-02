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
public class PaymentDto extends AuditModel {
    private Integer paymentId;
    private Integer userId;
    private Integer packageId;
    private Float amount;
    private String status;
    private LocalDateTime time;
}
