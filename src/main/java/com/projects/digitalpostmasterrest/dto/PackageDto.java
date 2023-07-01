package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto extends AuditModel {

    private String packageId;
    private String sender;
    private String receiver;
    private String receiverAddress;
    private String dimensions;
    private Float weight;
    private String instructions;

}
