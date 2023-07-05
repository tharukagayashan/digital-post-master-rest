package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.AgentDto;
import com.projects.digitalpostmasterrest.dto.custom.AgentCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface AgentService {
    ResponseEntity createAgent(AgentCreateReqDto agentCreateReqDto);

    ResponseEntity getAllAgents();

    ResponseEntity deleteAgent(Integer agentId);

    ResponseEntity updateAgent(AgentDto agentDto);

    ResponseEntity getAgentById(Integer agentId);
}
