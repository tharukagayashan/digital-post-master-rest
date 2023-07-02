package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Integer> {
}
