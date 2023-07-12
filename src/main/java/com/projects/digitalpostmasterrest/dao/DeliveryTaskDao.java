package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.DeliveryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryTaskDao extends JpaRepository<DeliveryTask,Integer> {

    @Query("SELECT D FROM DeliveryTask D WHERE D.referenceNo LIKE %?1%")
    List<DeliveryTask> searchDeliveryTaskByReferenceNo(String referenceNo);
}
