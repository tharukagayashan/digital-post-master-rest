package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends MongoRepository<Payment,String> {
}
