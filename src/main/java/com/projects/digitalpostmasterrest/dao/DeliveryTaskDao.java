package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.DeliveryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTaskDao extends JpaRepository<DeliveryTask,Integer> {
}
