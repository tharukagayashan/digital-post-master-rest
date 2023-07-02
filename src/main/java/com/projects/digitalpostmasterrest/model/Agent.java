package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
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
}
