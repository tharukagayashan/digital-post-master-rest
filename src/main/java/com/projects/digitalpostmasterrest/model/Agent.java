package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AGENT")
public class Agent extends AuditModel {
    @Id
    private String agentId;
    @Field
    private String name;
    @Field
    private String contactNo;
    @Field
    private String currentLocation;
}
