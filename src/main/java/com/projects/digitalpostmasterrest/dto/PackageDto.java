package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto extends AuditModel {

    private String packageId;
    private String sender;
    private String receiver;
    private String dimensions;
    private Float weight;
    private String instructions;
    private Float fee;

}
