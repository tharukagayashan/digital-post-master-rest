package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {
}
