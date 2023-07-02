package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto extends AuditModel {
    private Integer agentId;
    private String name;
    private String contactNo;
    private String currentLocation;
}
