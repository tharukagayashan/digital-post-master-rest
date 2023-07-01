package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "PACKAGE")
public class Package extends AuditModel {

    @Id
    private String packageId;
    @Field
    private String sender;
    @Field
    private String receiver;
    @Field
    private String receiverAddress;
    @Field
    private String dimensions;
    @Field
    private Float weight;
    @Field
    private String instructions;

}
