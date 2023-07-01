package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentDao extends MongoRepository<Agent,String> {
}
