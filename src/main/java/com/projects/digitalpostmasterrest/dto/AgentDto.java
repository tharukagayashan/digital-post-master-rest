package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.model.Agent;
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
    private Integer userId;

    public Agent toEntity() {
        UserDetailDao userDetailDao = null;
        Agent agent = new Agent();
        agent.setAgentId(this.getAgentId());
        agent.setName(this.getName());
        agent.setContactNo(this.getContactNo());
        agent.setCurrentLocation(this.getCurrentLocation());
        agent.setUserDetail(userDetailDao.findById(this.getUserId()).get());
        agent.setCreatedDate(this.getCreatedDate());
        agent.setCreatedBy(this.getCreatedBy());
        return agent;
    }

}