package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserDetail, Integer> {
}
