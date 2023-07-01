package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.DeliveryTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTaskDao extends MongoRepository<DeliveryTask,String> {
}
