package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.AgentDto;
import com.projects.digitalpostmasterrest.dto.custom.AgentCreateReqDto;
import com.projects.digitalpostmasterrest.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/create")
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentCreateReqDto agentCreateReqDto) {
        ResponseEntity response = agentService.createAgent(agentCreateReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        ResponseEntity response = agentService.getAllAgents();
        return response;
    }

    @DeleteMapping("/delete/{agentId}")
    public ResponseEntity<Integer> deleteAgent(@PathVariable Integer agentId) {
        ResponseEntity response = agentService.deleteAgent(agentId);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<AgentDto> updateAgent(@RequestBody AgentDto agentDto) {
        ResponseEntity response = agentService.updateAgent(agentDto);
        return response;
    }

    @GetMapping("/get/{agentId}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable Integer agentId) {
        ResponseEntity response = agentService.getAgentById(agentId);
        return response;
    }

}
