package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.AgentDto;
import com.projects.digitalpostmasterrest.dto.custom.AgentCreateReqDto;
import com.projects.digitalpostmasterrest.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/create")
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentCreateReqDto agentCreateReqDto){
        ResponseEntity response = agentService.createAgent(agentCreateReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AgentDto>> getAllAgents(){
        ResponseEntity response = agentService.getAllAgents();
        return response;
    }

}
