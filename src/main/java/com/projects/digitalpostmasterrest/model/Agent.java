package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.AgentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AGENT")
public class Agent extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGENT_ID")
    private Integer agentId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "CURRENT_LOCATION")
    private String currentLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private UserDetail userDetail;

    public AgentDto toDto() {
        AgentDto agentDto = new AgentDto();
        agentDto.setAgentId(this.getAgentId());
        agentDto.setName(this.getName());
        agentDto.setContactNo(this.getContactNo());
        agentDto.setCurrentLocation(this.getCurrentLocation());
        agentDto.setCreatedDate(this.getCreatedDate());
        agentDto.setCreatedBy(this.getCreatedBy());
        agentDto.setUserId(this.getUserDetail().getUserId());
        return agentDto;
    }

}
