package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentDao extends JpaRepository<Agent,Integer> {

    Optional<Agent> findByUserDetailUserId(Integer userId);

}
