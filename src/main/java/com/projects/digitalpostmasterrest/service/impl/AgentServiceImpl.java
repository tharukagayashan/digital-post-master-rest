package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.AgentDao;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.AgentDto;
import com.projects.digitalpostmasterrest.dto.custom.AgentCreateReqDto;
import com.projects.digitalpostmasterrest.enums.UserTypeEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.Agent;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.projects.digitalpostmasterrest.constant.Contants.*;

@Slf4j
@Service
public class AgentServiceImpl implements AgentService {
    private final UserDetailDao userDao;
    private final AgentDao agentDao;

    public AgentServiceImpl(AgentDao agentDao, UserDetailDao userDao) {
        this.agentDao = agentDao;
        this.userDao = userDao;
    }

    @Override
    public ResponseEntity createAgent(AgentCreateReqDto agentCreateReqDto) {
        try {

            Optional<Agent> optAgent = agentDao.findByUserDetailUserId(agentCreateReqDto.getUserId());
            if (optAgent.isPresent()){
                throw new ErrorAlert(AGENT_ALREADY_EXIST,"400");
            }

            Optional<UserDetail> optUser = userDao.findById(agentCreateReqDto.getUserId());
            if (!optUser.isPresent()) {
                throw new ErrorAlert(USER_NOT_FOUND, "400");
            } else {

                UserDetail user = optUser.get();

                Agent agent = new Agent();
                agent.setName(user.getName());
                agent.setContactNo(user.getContactNo());
                agent.setCurrentLocation(user.getAddress());
                agent.setUserDetail(user);

                agent = agentDao.save(agent);
                if (agent == null) {
                    throw new ErrorAlert(AGENT_CREATE_ERROR, "400");
                } else {
                    user.setUserType(UserTypeEnum.AGENT.name());
                    user = userDao.save(user);
                    log.info("User updated with new user type : {}", user.toDto());
                    return ResponseEntity.ok(agent.toDto());
                }
            }
        } catch (Exception e) {
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getAllAgents() {
        List<Agent> agentList = agentDao.findAll();
        if (agentList.isEmpty()) {
            throw new ErrorAlert(AGENT_LIST_EMPTY, "400");
        } else {
            List<AgentDto> agentDtoList = new ArrayList<>();
            for (Agent a : agentList) {
                agentDtoList.add(a.toDto());
            }
            return ResponseEntity.ok(agentDtoList);
        }
    }
}